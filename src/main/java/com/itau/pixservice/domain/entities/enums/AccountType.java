package com.itau.pixservice.domain.entities.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AccountType {
    CORRENTE("Corrente"),
    POUPANCA("Poupança");

    private String description;

    AccountType(String description) {
        this.description = description;
    }

    public static AccountType get(String description) {
        return Arrays.stream(values()).filter(t -> t.getDescription() == description).findFirst().orElse(null);
    }
}
