package com.itau.pixservice.resources.repositories.adapters;

import com.itau.pixservice.domain.entities.PixKey;
import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.resources.repositories.entities.AccountJpa;
import com.itau.pixservice.resources.repositories.entities.PixKeyJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PixKeyAdapter {

    @Autowired
    private KeyTypeAdapter keyTypeAdapter;

    public PixKeyJpa toJpa(PixKey pixKey, AccountJpa accountJpa){
        PixKeyJpa pixKeyJpa = new PixKeyJpa();
        pixKeyJpa.setKeyType(keyTypeAdapter.toJpa(pixKey.getKeyType()));
        pixKeyJpa.setValue(pixKey.getValue());
        pixKeyJpa.setAccount(accountJpa);

        return pixKeyJpa;
    }

    public PixKey toDomain(PixKeyJpa pixKeyJpa){
        PixKey pixKey = new PixKey();
        pixKey.setKeyType(keyTypeAdapter.toDomain(pixKeyJpa.getKeyType()));
        pixKey.setValue(pixKeyJpa.getValue());

        return pixKey;
    }

    public PixResponse toResponse (PixKeyJpa pixKeyJpa){
        PixResponse response = new PixResponse();
        response.setId(pixKeyJpa.getPixKeyId().toString());
        response.setTipoChave(pixKeyJpa.getKeyType().getDescription());
        response.setValorChave(pixKeyJpa.getValue());
        response.setTipoConta(pixKeyJpa.getAccount().getAccountType());
        response.setNumeroAgencia(pixKeyJpa.getAccount().getBranchNumber());
        response.setNumeroConta(pixKeyJpa.getAccount().getAccountNumber());
        response.setTipoPessoa(pixKeyJpa.getAccount().getClient().getPersonType());
        response.setNomeCorrentista(pixKeyJpa.getAccount().getClient().getFirstName());
        response.setSobrenomeCorrentista(pixKeyJpa.getAccount().getClient().getLastName());
        response.setDatahoraInclusao(pixKeyJpa.getCreatedAt());
        response.setDatahoraInativacao(pixKeyJpa.getUpdatedAt());

        return response;
    }
}
