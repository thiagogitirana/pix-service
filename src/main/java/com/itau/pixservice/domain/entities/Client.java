package com.itau.pixservice.domain.entities;

import lombok.Data;

import java.util.List;

@Data
public class Client {

    private String firstName;
    private String lastName;
    private String personType;
    private List<Account> accounts;
}
