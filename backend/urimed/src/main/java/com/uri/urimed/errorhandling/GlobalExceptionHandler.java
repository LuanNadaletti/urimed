package com.uri.urimed.errorhandling;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(@NotNull Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An internal error occurred:\n" + e.getMessage()
                + " " + e.getClass().getName());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(@NotNull HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred when trying to convert JSON:\n" + e.getMessage());
    }

}
