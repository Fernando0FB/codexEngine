package com.back.codex.mapper;

import com.back.codex.dto.request.RacaRequest;
import com.back.codex.dto.response.RacaResponse;
import com.back.codex.model.Raca;

public class RacaMapper {

    public static RacaResponse toResponse(Raca raca) {
        return new RacaResponse(
                raca.getNome(),
                raca.getEfeitos().stream()
                        .map(EfeitoMapper::toResponse)
                        .toList()
        );
    }

    public static Raca toEntity(RacaRequest racaRequest) {
        Raca raca = new Raca();
        raca.setNome(racaRequest.nome());
        return raca;
    }
}
