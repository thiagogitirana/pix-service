package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.exceptions.NotFoundException;
import com.itau.pixservice.domain.gateways.repositories.PixKeyRepository;
import com.itau.pixservice.resources.repositories.adapters.PixKeyAdapter;
import com.itau.pixservice.resources.repositories.entities.PixKeyJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PixKeyRepositoryImpl implements PixKeyRepository {

    @Autowired
    private PixKeyRepositoryJpa pixKeyRepositoryJpa;

    @Autowired
    private PixKeyAdapter pixKeyAdapter;

    @Override
    public boolean exists(String key) {
        return pixKeyRepositoryJpa.existsByValue(key);
    }

    @Override
    public PixResponse findById(String id) {

        Optional<PixKeyJpa> response = pixKeyRepositoryJpa.findBypixKeyId(id);

        if (response.isPresent()) {
            return pixKeyAdapter.toResponse(response.get());
        }else {
            throw new NotFoundException("Chave pix não encontrada");
        }
    }
}
