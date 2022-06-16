package com.itau.pixservice.application.web.exceptions;

import com.itau.pixservice.application.web.dto.response.PixErrorDto;
import com.itau.pixservice.domain.exceptions.InvalidKeyException;
import com.itau.pixservice.domain.exceptions.InvalidParamiterException;
import com.itau.pixservice.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {InvalidKeyException.class, InvalidParamiterException.class})
    public ResponseEntity<PixErrorDto> validationErros(Exception exception) {

        PixErrorDto error = new PixErrorDto();
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<PixErrorDto> validationNotFoundErros(Exception exception) {

        PixErrorDto error = new PixErrorDto();
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
