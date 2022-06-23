package com.itau.pixservice.domain.gateways.repositories;

import com.itau.pixservice.domain.entities.PixResponse;

import java.util.List;

public interface PixKeyRepository {
    boolean exists(String key);
    PixResponse findById(String id);
    PixResponse remove(String id);
    List<PixResponse> find(String id, String keyType, Integer branch, Integer account, String name,
                           String insertDate, String deleteDate);
}
