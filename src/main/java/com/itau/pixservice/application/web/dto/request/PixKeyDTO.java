package com.itau.pixservice.application.web.dto.request;

import com.itau.pixservice.domain.entities.enums.KeyType;
import lombok.Data;

@Data
public class PixKeyDTO {
    private String value;
    private KeyType keyType;
}
