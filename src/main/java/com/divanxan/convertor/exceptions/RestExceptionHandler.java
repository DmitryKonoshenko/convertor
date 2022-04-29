package com.divanxan.convertor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CurrencyException.class)
    public ResponseEntity<ErrorResponse> exceptionToDoHandler(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage()).build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .message("The request is malformed, " + ex.getMessage()).build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
