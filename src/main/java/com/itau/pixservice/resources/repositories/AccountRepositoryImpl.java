package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.gateways.repositories.AccountRepository;
import com.itau.pixservice.resources.repositories.adapters.ClientAdapter;
import com.itau.pixservice.resources.repositories.entities.AccountJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountRepositoryJpa accountRepositoryJpa;
    @Autowired
    private ClientAdapter clientAdapter;

    @Override
    public Client findAccountClient(Integer branchNumber, Integer accountNumber) {

        Optional<AccountJpa> accountJpa =  accountRepositoryJpa.findByBranchNumberAndAccountNumber(branchNumber, accountNumber);

        if(accountJpa.isPresent()){
            return clientAdapter.toDomain(accountJpa.get().getClient());
        }

        return null;
    }
}
