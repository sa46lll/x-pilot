package org.xangle.xpilot.core.exception;

import java.time.LocalDateTime;
import java.util.Arrays;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String trace
) {

    public static ErrorResponse of(Exception e, ErrorType errorType, String customMessage) {
        return new ErrorResponse(
                LocalDateTime.now(),
                errorType.getStatus(),
                errorType.getError(),
                customMessage,
                Arrays.toString(e.getStackTrace())
        );
    }
}
