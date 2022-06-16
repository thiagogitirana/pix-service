package com.itau.pixservice.application.web.dto.request;

import com.itau.pixservice.domain.entities.enums.AccountType;
import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {

    private Integer branchNumber;
    private Integer accountNumber;
    private AccountType accountType;
    private List<PixKeyDTO> pixKeys;
}
