package com.back.codex.dto.response;

public record EfeitoResponse(
        String descricao,
        AtributoResponse atributoVinculado,
        String tipoEfeito,
        String tipoModificacao,
        Integer quantidade
) {

}