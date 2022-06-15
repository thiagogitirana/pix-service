package com.itau.pixservice.application.web.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {

    private String firstName;
    private String lastName;
    private String personType;
    private List<AccountDTO> accounts;
}
