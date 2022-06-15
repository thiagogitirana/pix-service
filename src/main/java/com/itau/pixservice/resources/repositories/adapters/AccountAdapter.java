package com.itau.pixservice.resources.repositories.adapters;

import com.itau.pixservice.domain.entities.Account;
import com.itau.pixservice.resources.repositories.entities.AccountJpa;
import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountAdapter {

    @Autowired
    private PixKeyAdapter pixKeyAdapter;

    public AccountJpa toJpa(Account account, ClientJpa clientJpa){
        AccountJpa accountJpa = new AccountJpa();
        accountJpa.setAccountNumber(account.getAccountNumber());
        accountJpa.setBranchNumber(account.getBranchNumber());
        accountJpa.setAccountType(account.getAccountType());
        accountJpa.setClient(clientJpa);
        accountJpa.setPixKeys(account.getPixKeys().stream().map(key -> pixKeyAdapter.toJpa(key,accountJpa)).collect(Collectors.toList()));

        return accountJpa;
    }

    public Account toDomain(AccountJpa accountJpa){
        Account account = new Account();
        account.setAccountNumber(accountJpa.getAccountNumber());
        account.setBranchNumber(accountJpa.getBranchNumber());
        account.setAccountType(accountJpa.getAccountType());
        account.setPixKeys(accountJpa.getPixKeys().stream().map(key -> pixKeyAdapter.toDomain(key)).collect(Collectors.toList()));

        return account;
    }
}
