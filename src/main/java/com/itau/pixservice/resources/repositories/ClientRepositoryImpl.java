package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.gateways.repositories.ClientRepository;
import com.itau.pixservice.resources.repositories.adapters.ClientAdapter;
import com.itau.pixservice.resources.repositories.entities.ClientJpa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientRepositoryJpa clientRepositoryJpa;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientAdapter clientAdapter;

    @Override
    public void save(Client client) {
        ClientJpa clientJpa = null;

        clientJpa = clientAdapter.toJpa(client);

        try{
            clientRepositoryJpa.save(clientJpa);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

}
