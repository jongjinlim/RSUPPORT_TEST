package com.example.rsupporttest;


import com.example.rsupporttest.error.CoreException;
import com.example.rsupporttest.error.ErrorType;
import com.example.rsupporttest.error.response.CoreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CoreResponse<?>> handleApiException(MethodArgumentNotValidException e) {
        ErrorType error = ErrorType.FIELD_ERROR;
        return new ResponseEntity<>(
                CoreResponse.error(error, e.getBindingResult().getFieldErrors()), error.getStatus());
    }

    /**
     * 비즈니스 로직에서 발생하는 ApiException 을 응답 규격에 맞춰 {@link ResponseEntity}를 생성한다. ApiException 의 LogLevel 에
     * 맞는 로그를 출력한다.
     *
     * @param
     * @return ResponseEntity
     */
    @ExceptionHandler(CoreException.class)
    public ResponseEntity<CoreResponse<?>> handleApiException(CoreException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error("ApiException: {}", e.getMessage(), e);
            case WARN -> log.warn("ApiException: {}", e.getMessage(), e);
            default -> log.info("ApiException: {}", e.getMessage(), e);
        }
        return new ResponseEntity<>(
                CoreResponse.error(e.getErrorType(), e.getData()), e.getErrorType().getStatus());
    }

    /**
     * 의도하지 않은 Exception(!= ApiException) 이 발생한 경우는 DEFAULT_ERROR 을 발생시킨다.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CoreResponse<?>> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        return new ResponseEntity<>(
                CoreResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.getStatus());
    }
}
