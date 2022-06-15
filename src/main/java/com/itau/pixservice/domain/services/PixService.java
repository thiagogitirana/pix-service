package com.itau.pixservice.domain.services;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.gateways.repositories.ClientRepository;
import com.itau.pixservice.domain.gateways.repositories.PixKeyRepository;
import com.itau.pixservice.domain.validators.KeyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PixService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PixKeyRepository pixKeyRepository;
    @Autowired
    private KeyValidator keyValidator;

    public void save(Client client){

        client.getAccounts().stream().forEach(a-> a.getPixKeys().stream().forEach(pixKey -> keyValidator.validate(pixKey)));
        clientRepository.save(client);
    }

    public PixResponse findById(String pixKeyId){
        return pixKeyRepository.findById(pixKeyId);
    }
}
