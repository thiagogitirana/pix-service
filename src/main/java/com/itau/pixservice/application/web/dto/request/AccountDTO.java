package com.itau.pixservice.application.web.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {

    private Integer branchNumber;
    private Integer accountNumber;
    private String accountType;
    private List<PixKeyDTO> pixKeys;
}
