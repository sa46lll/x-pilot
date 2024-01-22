package org.xangle.xpilot.core.exception;

import lombok.Getter;

public class ErrorTypeException extends RuntimeException {

    private final String message;
    private final ErrorType errorType;

    public ErrorTypeException(String message, ErrorType errorType) {
        this.message = message;
        this.errorType = errorType;
    }

    public String getCustomMessage() {
        return message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
