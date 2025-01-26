package com.example.rsupporttest.error;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

public interface CoreErrorType {
    HttpStatus getStatus();

    CoreErrorCode getCode();

    String getMessage();

    LogLevel getLogLevel();
}
