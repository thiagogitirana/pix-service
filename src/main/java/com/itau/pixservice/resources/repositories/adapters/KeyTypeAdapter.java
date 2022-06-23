package com.itau.pixservice.resources.repositories.adapters;

import com.itau.pixservice.domain.entities.enums.KeyType;
import com.itau.pixservice.resources.repositories.entities.KeyTypeJpa;
import org.springframework.stereotype.Component;

@Component
public class KeyTypeAdapter {

    public KeyTypeJpa toJpa(KeyType keyType){
        KeyTypeJpa keyTypeJpa = new KeyTypeJpa();
        keyTypeJpa.setKeyTypeId(keyType.getCode());
        keyTypeJpa.setDescription(keyType.getDescription());

        return keyTypeJpa;
    }

    public KeyType toDomain(KeyTypeJpa keyTypeJpa){
        return KeyType.valueOf(keyTypeJpa.getDescription());
    }

}
