package com.itau.pixservice.application.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixResponseDTO {
    private String id;
    private String tipoChave;
    private String valorChave;
    private String tipoConta;
    private Integer numeroAgencia;
    private Integer numeroConta;
    private String tipoPessoa;
    private String nomeCorrentista;
    private String sobrenomeCorrentista;
    private LocalDateTime datahoraInclusao;
    private LocalDateTime datahorainativacao;
}