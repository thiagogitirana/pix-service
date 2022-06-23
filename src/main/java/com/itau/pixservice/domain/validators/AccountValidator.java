package com.itau.pixservice.domain.validators;

import com.itau.pixservice.domain.entities.Account;
import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.exceptions.InvalidParamiterException;
import com.itau.pixservice.domain.gateways.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class AccountValidator {

    private static Integer BRANCH_MAX_SIZE = 9999;
    private static Integer ACCOUNT_MAX_SIZE = 99999999;

    @Autowired
    private KeyValidator keyValidator;

    @Autowired
    private AccountRepository accountRepository;

    public void validate(Account account, Client client){
        accountNullValidate(account);
        requiredFieldsValidate(account);
        fieldValidation(account);
        accountClienteValidate(client, account);
        keysValidator(account);

    }

    private void accountNullValidate(Account account){
        if(isNull(account)){
            throw new InvalidParamiterException("Os dados da conta são obrigatórios");
        }
    }

    private void requiredFieldsValidate(Account account){
        if(isNull(account.getAccountType())){
            throw new InvalidParamiterException("O tipo da conta é obrigatório");
        }

        if(account.getAccountNumber() == null || account.getAccountNumber() <= 0){
            throw new InvalidParamiterException("O número da conta é obrigatório");
        }

        if(account.getBranchNumber() == null || account.getBranchNumber() <= 0){
            throw new InvalidParamiterException("O número da agência é obrigatório");
        }
    }

    private void fieldValidation(Account account){
        if(account.getAccountNumber() > ACCOUNT_MAX_SIZE){
            throw new InvalidParamiterException("O número da conta não deve ter mais que 8 caracteres");
        }

        if(account.getBranchNumber() > BRANCH_MAX_SIZE){
            throw new InvalidParamiterException("O número da agência não deve ter mais que 4 caracteres");
        }
    }

    private void keysValidator(Account account){
        if(account.getPixKeys() == null || account.getPixKeys().isEmpty()){
            throw new InvalidParamiterException("A chave pix é obrigatória");
        }

        account.getPixKeys().stream().forEach(key -> keyValidator.validate(key));
    }

    public void accountClienteValidate(Client client, Account account){
        Client savedClient = accountRepository.findAccountClient(account.getBranchNumber(), account.getAccountNumber());

        if(savedClient != null && !savedClient.equals(client)){
            throw new InvalidParamiterException("A agência e conta informada, pertence a outro cliente");
        }
    }
}
