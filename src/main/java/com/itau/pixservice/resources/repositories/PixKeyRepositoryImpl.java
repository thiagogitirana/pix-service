package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.exceptions.NotFoundException;
import com.itau.pixservice.domain.gateways.repositories.PixKeyRepository;
import com.itau.pixservice.resources.repositories.adapters.PixKeyAdapter;
import com.itau.pixservice.resources.repositories.entities.PixKeyJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PixKeyRepositoryImpl implements PixKeyRepository {

    @Autowired
    private PixKeyRepositoryJpa pixKeyRepositoryJpa;

    @Autowired
    private PixKeyAdapter pixKeyAdapter;

    @Autowired
    private PixKeyDao pixKeyDao;

    @Override
    public boolean exists(String key) {
        return pixKeyRepositoryJpa.existsByValue(key);
    }

    @Override
    public PixResponse findById(String id) {
        List<PixKeyJpa> lista = pixKeyDao.find(id, null,null, null, null,
                null, null);

        if (!lista.isEmpty()) {
            return pixKeyAdapter.toResponse(lista.stream().findFirst().get());
        }else {
            throw new NotFoundException("Chave pix não encontrada");
        }
    }
}
