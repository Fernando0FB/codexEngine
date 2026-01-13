package com.back.codex.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PersonagemRequest(
        @NotBlank(message = "O nome deve ser informado")
        String nome,
        BigDecimal nivel,
        @NotNull(message = "A vida máxima deve ser informada")
        BigDecimal vidaMaxima,
        @NotNull(message = "A vida máxima deve ser informada")
        BigDecimal vidaAtual,
        BigDecimal sanidadeMaxima,
        BigDecimal sanidadeAtual,
        BigDecimal vontadeMaxima,
        BigDecimal vontadeAtual
) {
}
