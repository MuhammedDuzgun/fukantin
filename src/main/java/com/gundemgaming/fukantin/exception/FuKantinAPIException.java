package com.gundemgaming.fukantin.exception;

import org.springframework.http.HttpStatus;

public class FuKantinAPIException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public FuKantinAPIException(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
