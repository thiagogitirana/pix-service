package com.itau.pixservice.domain.entities;

import com.itau.pixservice.domain.entities.enums.AccountType;
import lombok.Data;

import java.util.List;

@Data
public class Account {

    private Integer branchNumber;
    private Integer accountNumber;
    private AccountType accountType;
    private List<PixKey> pixKeys;
}
