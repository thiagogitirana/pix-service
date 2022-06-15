package com.itau.pixservice.domain.entities.enums;

import lombok.Getter;

@Getter
public enum PersonType {
    PF("F"),
    PJ("J");

    private String description;

    PersonType(String description) {
        this.description = description;
    }
}
