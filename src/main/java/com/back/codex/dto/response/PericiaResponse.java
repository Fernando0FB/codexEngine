package com.back.codex.dto.response;

public record PericiaResponse(
    Long id,
    String nome,
    AtributoResponse atributoBase
) {
}
