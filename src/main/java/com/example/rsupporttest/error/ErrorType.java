package com.example.rsupporttest.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

import static com.example.rsupporttest.error.ErrorCode.*;


@Getter
@RequiredArgsConstructor
public enum ErrorType implements CoreErrorType {
    // 클라이언트 에러
    FIELD_ERROR(HttpStatus.BAD_REQUEST, A0001, "잘못된 필드가 포함된 요청입니다.", LogLevel.WARN),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, A0002, "요청에 맞는 권한이 아닙니다.", LogLevel.WARN),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, A0003, "인증이 필요한 요청입니다.", LogLevel.WARN),

    // 서버 에러
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, B0001, "예상치 못한 오류가 발생했습니다.", LogLevel.ERROR),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, B0002, "이미 존재하는 이메일입니다.", LogLevel.WARN),
	LOGIN_FAILED(HttpStatus.BAD_REQUEST, B0003, "로그인에 실패했습니다.", LogLevel.WARN),
    ;
    private final HttpStatus status;
    private final ErrorCode code;
    private final String message;
    private final LogLevel logLevel;
}
