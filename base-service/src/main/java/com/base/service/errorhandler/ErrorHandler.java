package com.base.service.errorhandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class ErrorHandler extends RuntimeException {

    private final HttpStatus status;

    public ErrorHandler(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
