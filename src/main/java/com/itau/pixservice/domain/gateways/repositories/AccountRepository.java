package com.itau.pixservice.domain.gateways.repositories;

import com.itau.pixservice.domain.entities.Client;

public interface AccountRepository {

    Client findAccountClient(Integer branchNumber, Integer accountNumber);
}
