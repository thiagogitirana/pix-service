package com.itau.pixservice.domain.validators;

import com.itau.pixservice.domain.exceptions.InvalidParamiterException;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class SearchValidation {

    public void validate(String id, String keyType, Integer branch, Integer account, String name,
                         String insertDate, String deleteDate) {

        idValidate(id, keyType, branch, account, name,
                insertDate, deleteDate);

        dateValidate(insertDate, deleteDate);

    }

    private void idValidate(String id, String keyType, Integer branch, Integer account, String name,
                            String insertDate, String deleteDate) {
        if (isNotBlank(id) && isNull(keyType, branch, account, name, insertDate, deleteDate)) {
            throw new InvalidParamiterException("Ao consultar pelo id, nenhum outro filtro deve ser informado");
        }
    }

    private void dateValidate(String insertDate, String deleteDate){
        if(isNotBlank(insertDate) && isNotBlank(deleteDate)){
            throw new InvalidParamiterException("Não pode ser informado data de inclusão e exclusão na mesma consulta");
        }
    }

    private boolean isNull(Object... objects) {
        for (Object object : objects) {
            if (object != null) {
                return true;
            }
        }
        return false;
    }
}
