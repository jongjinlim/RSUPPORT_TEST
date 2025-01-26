package com.example.rsupporttest.error;

/**
 * 에러 응답의 Response 형식
 *
 * @param code
 * @param message
 * @param data
 */
public record ErrorMessage(String code, String message, Object data) {

    public static ErrorMessage from(CoreErrorType errorType) {
        return new ErrorMessage(errorType.getCode().name(), errorType.getMessage(), null);
    }

    public static ErrorMessage from(CoreErrorType errorType, Object data) {
        return new ErrorMessage(errorType.getCode().name(), errorType.getMessage(), data);
    }
}
