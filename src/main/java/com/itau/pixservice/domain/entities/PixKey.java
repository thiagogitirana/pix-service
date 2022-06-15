package com.itau.pixservice.domain.entities;

import com.itau.pixservice.domain.entities.enums.KeyType;
import lombok.Data;

@Data
public class PixKey {
    private String value;
    private KeyType keyType;
}
