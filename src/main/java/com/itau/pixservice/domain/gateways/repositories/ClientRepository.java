package com.itau.pixservice.domain.gateways.repositories;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.entities.PixResponse;

import java.util.List;

public interface ClientRepository {
    List<PixResponse> save(Client client);

    int countClientKeys(String firstName, String lastName);

    Client findClientByFirstAndLastName(String firstName, String lastName);
}
