package com.back.codex.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ItemRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100)
        String nome,

        @NotBlank
        @Size(min = 3, max = 100)
        String descricao,

        @NotNull(message = "Peso é obrigatório")
        @DecimalMin(value = "0.0", message = "Peso não pode ser negativo")
        BigDecimal peso,

        @NotNull(message = "Quantidade é obrigatória")
        @Min(value = 1, message = "Quantidade mínima é 1")
        BigDecimal quantidade,

        @NotNull(message = "Equipado é obrigatório")
        Boolean equipado,

        @NotNull(message = "ID do personagem é obrigatório")
        @Positive
        Long personagemId
) {}