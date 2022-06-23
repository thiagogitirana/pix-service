package com.itau.pixservice.domain.validators;

import com.itau.pixservice.domain.entities.PixKey;
import com.itau.pixservice.domain.exceptions.InvalidKeyException;
import com.itau.pixservice.domain.exceptions.InvalidParamiterException;
import com.itau.pixservice.domain.gateways.repositories.PixKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class KeyValidator {

    private static Integer VALUE_MAX_SIZE = 77;
    private static Integer ALEATORIO_MAX_SIZE = 36;

    @Autowired
    private PhoneValidator phoneValidator;
    @Autowired
    private CpfCnpjValidator cpfCnpjValidator;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private PixKeyRepository pixKeyRepository;

    public void validate(PixKey pixKey) {

        pixKeyNullValidate(pixKey);
        maxSizeValidate(pixKey);
        exists(pixKey);

        switch (pixKey.getKeyType()) {
            case CELULAR -> validatePhone(pixKey.getValue());
            case EMAIL -> validateEmail(pixKey.getValue());
            case CPF -> validateCpf(pixKey.getValue());
            case CNPJ -> validateCnpj(pixKey.getValue());
            case ALEATORIO -> aleatorioMaxSizeValidate(pixKey.getValue());
        }
    }

    private void pixKeyNullValidate(PixKey pixKey) {
        if (isNull(pixKey)) {
            throw new InvalidParamiterException("A chave pix é obrigatória");
        }

        if (isBlank(pixKey.getValue())) {
            throw new InvalidParamiterException("A chave pix é obrigatória");
        }

        if (isNull(pixKey.getKeyType())) {
            throw new InvalidParamiterException("A chave pix é obrigatória");
        }
    }

    private void maxSizeValidate(PixKey pixKey) {
        if (pixKey.getValue().length() > VALUE_MAX_SIZE) {
            throw new InvalidParamiterException("A chave pix excedeu o limite máximo");
        }
    }

    private void aleatorioMaxSizeValidate(String key) {
        if (key.length() > ALEATORIO_MAX_SIZE) {
            throw new InvalidParamiterException("A chave pix excedeu o limite máximo");
        }
    }

    private void exists(PixKey pixKey) {
        if (pixKeyRepository.exists(pixKey.getValue())) {
            throw new InvalidKeyException("A chave informada já foi cadastrada");
        }
    }

    private void validatePhone(String key) {
        if (!phoneValidator.isValid(key)) {
            throw new InvalidKeyException("O Celular informado é inválido");
        }
    }

    private void validateEmail(String key) {
        if (!emailValidator.isValid(key)) {
            throw new InvalidKeyException("O E-mail informado é inválido");
        }
    }

    private void validateCpf(String key) {
        if (!cpfCnpjValidator.isValidCPF(key)) {
            throw new InvalidKeyException("O CPF informado é inválido");
        }
    }

    private void validateCnpj(String key) {
        if (!cpfCnpjValidator.isValidCNPJ(key)) {
            throw new InvalidKeyException("O CNPJ informado é inválido");
        }
    }
}
