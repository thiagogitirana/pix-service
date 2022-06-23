package com.itau.pixservice.domain.validators;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.exceptions.InvalidParamiterException;
import com.itau.pixservice.domain.gateways.repositories.AccountRepository;
import com.itau.pixservice.domain.gateways.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ClientValidator {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountValidator accountValidator;

    private static int PF_CLIENT_KEYS_LIMIT = 5;
    private static int PJ_CLIENT_KEYS_LIMIT = 20;

    public void validate(Client client) {
        clientNullValidate(client);
        fieldsValidate(client);
        keyLimitValidate(client);
        accountsValidator(client);
    }

    private void clientNullValidate(Client client) {
        if (isNull(client)) {
            throw new InvalidParamiterException("Os dados do correntista são obrigatórios");
        }
    }

    private void fieldsValidate(Client client) {
        if (isNull(client.getPersonType())) {
            throw new InvalidParamiterException("O tipo da pessoa é obrigatório");
        }

        if (isBlank(client.getFirstName())) {
            throw new InvalidParamiterException("O nome do correntista é obrigatório");
        }

        if (isBlank(client.getLastName())) {
            throw new InvalidParamiterException("O sobrenome do correntista é obrigatório");
        }
    }

    private void keyLimitValidate(Client client) {
        int clientKeys = clientRepository.countClientKeys(client.getFirstName(), client.getLastName());
        clientKeys += client.getAccounts().stream().mapToInt(account -> account.getPixKeys().size()).sum();

        switch (client.getPersonType()) {
            case F -> limitValidate(clientKeys, PF_CLIENT_KEYS_LIMIT);
            case J -> limitValidate(clientKeys, PJ_CLIENT_KEYS_LIMIT);
        }
    }

    private void limitValidate(int clientKeys, int limit) {
        if (clientKeys > limit) {
            throw new InvalidParamiterException("Limite de chaves excedido para este cliente");
        }
    }

    private void accountsValidator(Client client){
        if(client.getAccounts() == null || client.getAccounts().isEmpty()){
            throw new InvalidParamiterException("Os dados da conta são obrigatórios");
        }

        client.getAccounts().stream().forEach(account -> accountValidator.validate(account, client));
    }

}
