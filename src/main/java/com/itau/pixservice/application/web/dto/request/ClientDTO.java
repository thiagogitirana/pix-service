package com.itau.pixservice.application.web.dto.request;

import com.itau.pixservice.domain.entities.enums.PersonType;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {

    private String firstName;
    private String lastName;
    private PersonType personType;
    private List<AccountDTO> accounts;
}
