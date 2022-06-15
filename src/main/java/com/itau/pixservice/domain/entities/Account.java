package com.itau.pixservice.domain.entities;

import lombok.Data;

import java.util.List;

@Data
public class Account {

    private Integer branchNumber;
    private Integer accountNumber;
    private String accountType;
    private List<PixKey> pixKeys;
}
