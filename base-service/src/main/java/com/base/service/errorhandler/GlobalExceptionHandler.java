package com.base.service.errorhandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorHandler.class)
    public ResponseEntity<ErrorResponse> handleErrorHandler(ErrorHandler ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getStatus(),
                request.getDescription(false),
                ex.getStatus().getReasonPhrase()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @RequiredArgsConstructor
    @Getter
    public static class ErrorResponse {
        private final String message;
        private final HttpStatus status;
        private final String path;
        private final String error;
    }
}
