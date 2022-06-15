package com.itau.pixservice.domain.gateways.repositories;

import com.itau.pixservice.domain.entities.PixResponse;

public interface PixKeyRepository {

    boolean exists(String key);

    PixResponse findById(String id);

}
