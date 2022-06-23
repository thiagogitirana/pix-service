package com.itau.pixservice.domain.services;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.gateways.repositories.ClientRepository;
import com.itau.pixservice.domain.gateways.repositories.PixKeyRepository;
import com.itau.pixservice.domain.validators.ClientValidator;
import com.itau.pixservice.domain.validators.SearchValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PixService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PixKeyRepository pixKeyRepository;
    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private SearchValidation searchValidation;

    @Transactional
    public List<PixResponse> save(Client client) {

        clientValidator.validate(client);

        return clientRepository.save(client);
    }

    @Transactional
    public PixResponse remove(String id) {
        return pixKeyRepository.remove(id);
    }

    public PixResponse findById(String pixKeyId) {
        return pixKeyRepository.findById(pixKeyId);
    }

    public List<PixResponse> find(String id, String keyType, Integer branch, Integer account, String name,
                                  String insertDate, String deleteDate) {

        searchValidation.validate(id, keyType, branch, account, name,
                insertDate, deleteDate);

        return pixKeyRepository.find(id, keyType, branch, account, name,
                insertDate, deleteDate);
    }

}
