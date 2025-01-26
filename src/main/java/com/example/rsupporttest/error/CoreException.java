package com.example.rsupporttest.error;

import lombok.Getter;

/** 비즈니스 로직에서 발생하는 에러를 제어합니다. */
@Getter
public class CoreException extends RuntimeException {

    private final CoreErrorType errorType;
    private final Object data;

    private CoreException(CoreErrorType errorType, Object data) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = data;
    }

    public static CoreException of(CoreErrorType errorType, Object data) {
        return new CoreException(errorType, data);
    }

    public static CoreException of(CoreErrorType errorType) {
        return new CoreException(errorType, null);
    }
}
