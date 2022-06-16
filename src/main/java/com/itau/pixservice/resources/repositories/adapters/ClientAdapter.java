package com.itau.pixservice.resources.repositories.adapters;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.entities.enums.PersonType;
import com.itau.pixservice.resources.repositories.ClientRepositoryJpa;
import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientAdapter {

    @Autowired
    private AccountAdapter accountAdapter;
    @Autowired
    private ClientRepositoryJpa clientRepositoryJpa;

    public ClientJpa toJpa(Client client) {
        ClientJpa clientJpa = new ClientJpa();

        Optional<ClientJpa> savedClient = clientRepositoryJpa.findByFirstNameAndLastName(client.getFirstName(), client.getLastName());
        if (savedClient.isPresent()) {
            clientJpa.setClientId(savedClient.get().getClientId());
            clientJpa.setCreatedAt(savedClient.get().getCreatedAt());
        }

        clientJpa.setFirstName(client.getFirstName());
        clientJpa.setLastName(client.getLastName());
        clientJpa.setPersonType(client.getPersonType().getDescription());
        clientJpa.setAccounts(client.getAccounts().stream().map(acc -> accountAdapter.toJpa(acc, clientJpa)).collect(Collectors.toList()));

        return clientJpa;
    }

    public Client toDomain(ClientJpa clientJpa){
        Client client = new Client();
        client.setFirstName(clientJpa.getFirstName());
        client.setLastName(clientJpa.getLastName());
        client.setPersonType(PersonType.valueOf(clientJpa.getPersonType()));
        client.setAccounts(clientJpa.getAccounts().stream().map(acc-> accountAdapter.toDomain(acc)).collect(Collectors.toList()));

        return client;
    }
}
