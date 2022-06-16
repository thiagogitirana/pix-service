package com.itau.pixservice.domain.entities.enums;

import lombok.Getter;

@Getter
public enum PersonType {
    F("F"),
    J("J");

    private String description;

    PersonType(String description) {
        this.description = description;
    }

}
