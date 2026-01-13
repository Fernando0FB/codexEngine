package com.back.codex.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ClasseRequest(
        @NotBlank(message = "O 'nome' é obrigatório")
        String nome,
        @NotNull(message = "O 'nível' é obrigatório")
        BigDecimal nivel,
        @NotNull(message = "O 'personagemId' é obrigatório")
        Long personagemId
) {
}
