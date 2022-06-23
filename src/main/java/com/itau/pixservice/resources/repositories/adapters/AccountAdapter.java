package com.itau.pixservice.resources.repositories.adapters;

import com.itau.pixservice.domain.entities.Account;
import com.itau.pixservice.domain.entities.enums.AccountType;
import com.itau.pixservice.resources.repositories.AccountRepositoryJpa;
import com.itau.pixservice.resources.repositories.entities.AccountJpa;
import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountAdapter {

    @Autowired
    private PixKeyAdapter pixKeyAdapter;
    @Autowired
    private AccountRepositoryJpa accountRepositoryJpa;

    public AccountJpa toJpa(Account account, ClientJpa clientJpa){
        AccountJpa accountJpa = new AccountJpa();

        Optional<AccountJpa> savedAccount =  accountRepositoryJpa.findByBranchNumberAndAccountNumber(
                account.getBranchNumber(), account.getAccountNumber());
        if(savedAccount.isPresent()){
            accountJpa.setAccountId(savedAccount.get().getAccountId());
            accountJpa.setCreatedAt(savedAccount.get().getCreatedAt());
        }

        accountJpa.setAccountNumber(account.getAccountNumber());
        accountJpa.setBranchNumber(account.getBranchNumber());
        accountJpa.setAccountType(account.getAccountType().getDescription());
        accountJpa.setClient(clientJpa);
        accountJpa.setPixKeys(account.getPixKeys().stream().map(key -> pixKeyAdapter.toJpa(key,accountJpa)).collect(Collectors.toList()));

        return accountJpa;
    }

    public Account toDomain(AccountJpa accountJpa){
        Account account = new Account();
        account.setAccountNumber(accountJpa.getAccountNumber());
        account.setBranchNumber(accountJpa.getBranchNumber());
        account.setAccountType(AccountType.get(accountJpa.getAccountType()));
        account.setPixKeys(accountJpa.getPixKeys().stream().map(key -> pixKeyAdapter.toDomain(key)).collect(Collectors.toList()));

        return account;
    }
}
