package com.back.codex.mapper;

import com.back.codex.dto.request.TracoRequest;
import com.back.codex.dto.response.TracoResponse;
import com.back.codex.model.Traco;

public class TracoMapper {

    public static TracoResponse toResponse(Traco traco) {
        return new TracoResponse(
                traco.getId(),
                traco.getNome(),
                traco.getEfeitos().stream().map(EfeitoMapper::toResponse).toList()
        );
    }

    public static Traco toEntity(TracoRequest request) {
        Traco traco = new Traco();
        traco.setNome(request.nome());
        return traco;
    }

}
