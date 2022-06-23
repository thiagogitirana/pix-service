package com.itau.pixservice.domain.entities.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum KeyType{

    CELULAR("CELULAR", 1),
    EMAIL("EMAIL", 2),
    CPF("CPF", 3),
    CNPJ("CNPJ", 4),
    ALEATORIO("ALEATORIO", 5);

    private String description;
    private Integer code;

    KeyType(String description, Integer code) {
        this.description = description;
        this.code = code;
    }
}
