package com.itau.pixservice.resources.repositories;

import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.entities.enums.Status;
import com.itau.pixservice.domain.exceptions.InvalidParamiterException;
import com.itau.pixservice.domain.exceptions.NotFoundException;
import com.itau.pixservice.domain.gateways.repositories.PixKeyRepository;
import com.itau.pixservice.resources.repositories.adapters.PixKeyAdapter;
import com.itau.pixservice.resources.repositories.entities.PixKeyJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
        List<PixKeyJpa> lista = pixKeyDao.find(id, null, null, null, null,
                null, null);

        if (!lista.isEmpty()) {
            return pixKeyAdapter.toResponse(lista.stream().findFirst().get());
        } else {
            throw new NotFoundException("Chave pix não encontrada");
        }
    }

    @Override
    public PixResponse remove(String id) {

        Optional<PixKeyJpa> savedPixKey = pixKeyRepositoryJpa.findBypixKeyId(UUID.fromString(id));

        if (savedPixKey.isPresent()) {
            PixKeyJpa pix = savedPixKey.get();

            if (pix.getStatus().equals(Status.INACTIVE.name())) {
                throw new InvalidParamiterException("A chave já esta desativada");
            }

            pixKeyRepositoryJpa.deleteByPixKeyId(UUID.fromString(id));
            savedPixKey = pixKeyRepositoryJpa.findBypixKeyId(UUID.fromString(id));

            if (savedPixKey.isPresent()) {
                return pixKeyAdapter.toResponse(savedPixKey.get());
            } else {
                throw new NotFoundException("Chave pix não encontrada");
            }
        } else {
            throw new NotFoundException("Chave pix não encontrada");
        }
    }

    @Override
    public List<PixResponse> find(String id, String keyType, Integer branch, Integer account, String name, String insertDate, String deleteDate) {

        LocalDateTime deleteDateTime = getLocalDateTime(deleteDate);
        LocalDateTime insertDateTime = getLocalDateTime(insertDate);

        List<PixKeyJpa> pixKeyJpaList = pixKeyDao.find(id, keyType, branch, account, name,
                insertDateTime, deleteDateTime);

        if (pixKeyJpaList == null || pixKeyJpaList.isEmpty()) {
            throw new NotFoundException("Chave pix não encontrada");
        }

        return pixKeyJpaList.stream().map(pix -> pixKeyAdapter.toResponse(pix)).collect(Collectors.toList());
    }

    private LocalDateTime getLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (isNotBlank(date)) {
            return LocalDateTime.parse(date, formatter);
        }
        return null;
    }
}
