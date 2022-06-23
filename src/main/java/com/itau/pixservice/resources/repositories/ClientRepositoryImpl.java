package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.gateways.repositories.ClientRepository;
import com.itau.pixservice.resources.repositories.adapters.ClientAdapter;
import com.itau.pixservice.resources.repositories.adapters.PixKeyAdapter;
import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import com.itau.pixservice.resources.repositories.entities.PixKeyJpa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientRepositoryJpa clientRepositoryJpa;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientAdapter clientAdapter;

    @Autowired
    private PixKeyAdapter pixKeyAdapter;

    @Override
    public List<PixResponse> save(Client client) {
        ClientJpa clientJpa = clientAdapter.toJpa(client);

        clientJpa = clientRepositoryJpa.save(clientJpa);

        clientRepositoryJpa.save(clientJpa);

        return getPixResponses(clientJpa);
    }

    private List<PixResponse> getPixResponses(ClientJpa clientJpa) {
        List<PixKeyJpa> pixKeys = new ArrayList<>();

        clientJpa.getAccounts().stream().forEach(acc -> pixKeys.addAll(acc.getPixKeys()));

        return pixKeys.stream().map(pixKey -> pixKeyAdapter.toResponse(pixKey)).collect(Collectors.toList());
    }

    @Override
    public int countClientKeys(String firstName, String lastName) {
        return clientRepositoryJpa.countClientKeys(firstName, lastName);
    }

    @Override
    public Client findClientByFirstAndLastName(String firstName, String lastName) {
        Optional<ClientJpa> savedClient = clientRepositoryJpa.findByFirstNameAndLastName(firstName, lastName);
        if (savedClient.isPresent()) {
            return clientAdapter.toDomain(savedClient.get());
        }

        return null;
    }

}
