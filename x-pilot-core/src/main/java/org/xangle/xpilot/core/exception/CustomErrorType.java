package org.xangle.xpilot.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorType implements ErrorType {

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED)
    ;

    private final HttpStatus httpStatus;

    CustomErrorType(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public int getStatus() {
        return httpStatus.value();
    }

    @Override
    public String getError() {
        return httpStatus.getReasonPhrase();
    }
}