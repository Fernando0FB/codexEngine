package com.back.codex.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PericiaRequest(
        @NotBlank(message = "O 'nome' da perícia é obrigatório")
        String nome,
        @NotNull(message = "O 'atributoBaseId' da perícia é obrigatório")
        Long atributoBaseId
) {
}
