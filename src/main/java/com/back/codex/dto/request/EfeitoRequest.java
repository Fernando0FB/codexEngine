package com.back.codex.dto.request;

import com.back.codex.enums.TipoEfeito;
import com.back.codex.enums.TipoModificacao;
import com.back.codex.enums.TipoOrigem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EfeitoRequest(
        @NotBlank(message = "A 'descricao' é obrigatória")
        String descricao,

        @NotNull(message = "O 'atributoVinculadoId' é obrigatório")
        Long atributoVinculadoId,

        @NotNull(message = "O 'tipoEfeito' é obrigatório")
        TipoEfeito tipoEfeito,

        @NotNull(message = "O 'tipoModificacao' é obrigatório")
        TipoModificacao tipoModificacao,

        @NotNull(message = "A 'quantidade' é obrigatória")
        Integer quantidade,

        @NotNull(message = "O 'tipoOrigem' é obrigatório")
        TipoOrigem tipoOrigem,

        @NotNull(message = "O 'origemId' é obrigatório")
        @Positive(message = "O 'origemId' deve ser positivo")
        Long origemId
) {
}