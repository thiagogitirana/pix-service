package com.itau.pixservice.domain.exceptions;

public class InvalidParamiterException extends RuntimeException {

    private String message;

    public InvalidParamiterException(String message) {
        super(message);
        this.message = message;
    }
}
