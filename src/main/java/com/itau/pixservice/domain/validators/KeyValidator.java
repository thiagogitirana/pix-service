package com.itau.pixservice.domain.validators;

import com.itau.pixservice.domain.entities.PixKey;
import com.itau.pixservice.domain.exceptions.InvalidKeyException;
import com.itau.pixservice.domain.gateways.repositories.PixKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KeyValidator {

    @Autowired
    private PhoneValidator phoneValidator;
    @Autowired
    private CpfCnpjValidator cpfCnpjValidator;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private PixKeyRepository pixKeyRepository;

    public void validate(PixKey pixKey){

        exists(pixKey.getValue());

        switch (pixKey.getKeyType()){
            case CELULAR -> validatePhone(pixKey.getValue());
            case EMAIL -> validateEmail(pixKey.getValue());
            case CPF -> validateCpf(pixKey.getValue());
            case CNPJ -> cpfCnpjValidator.isValidCNPJ(pixKey.getValue());
        }

    }

    private void exists(String key){
        if (pixKeyRepository.exists(key)){
            throw new InvalidKeyException("A chave informada já foi cadastrada");
        }
    }

    private void validatePhone(String key){
        if (!phoneValidator.isValid(key)){
            throw new InvalidKeyException("O Celular informado é inválido");
        }
    }

    private void validateEmail(String key){
        if (!emailValidator.isValid(key)){
            throw new InvalidKeyException("O E-mail informado é inválido");
        }
    }

    private void validateCpf(String key){
        if (!cpfCnpjValidator.isValidCPF(key)){
            throw new InvalidKeyException("O CPF informado é inválido");
        }
    }

    private void validateCnpj(String key){
        if (!cpfCnpjValidator.isValidCNPJ(key)){
            throw new InvalidKeyException("O CNPJ informado é inválido");
        }
    }
}
