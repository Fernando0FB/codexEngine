package com.back.codex.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtributoRequest(
        @NotBlank(message = "O 'nome' do atributo é obrigatório")
        String nome,
        @NotNull(message = "O 'valorBase' deve ser informado")
        BigDecimal valorBase,
        @NotNull(message = "O 'valorAtual' deve ser informado")
        BigDecimal valorAtual,
        @NotNull(message = "O 'personagemId' é obrigatório")
        Long personagemId
) {
}
