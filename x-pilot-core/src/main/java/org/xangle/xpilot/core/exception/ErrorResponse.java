package org.xangle.xpilot.core.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String trace,
        String message,
        String path
) {

    public static ErrorResponse of(String customMessage, ErrorType errorType) {
        return new ErrorResponse(
                LocalDateTime.now(),
                errorType.getStatus(),
                errorType.getError(),
                null,
                customMessage,
                null
        );
    }
}
