package com.itau.pixservice.domain.exceptions;

import lombok.Getter;

@Getter
public class InvalidKeyException extends RuntimeException{

    private String message;

    public InvalidKeyException(String message) {
        super(message);
        this.message = message;
    }

}
