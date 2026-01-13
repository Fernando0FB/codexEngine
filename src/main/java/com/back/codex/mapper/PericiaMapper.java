package com.back.codex.mapper;

import com.back.codex.dto.request.PericiaRequest;
import com.back.codex.dto.response.PericiaResponse;
import com.back.codex.model.Atributo;
import com.back.codex.model.Pericia;

public class PericiaMapper {

    public static PericiaResponse toResponse(Pericia pericia) {
        return new PericiaResponse(
            pericia.getId(),
            pericia.getNome(),
            AtributoMapper.toResponse(pericia.getAtributoBase())
        );
    }

    public static Pericia toEntity(PericiaRequest request, Atributo atributo) {
        Pericia pericia = new Pericia();
        pericia.setNome(request.nome());
        pericia.setAtributoBase(atributo);
        return pericia;
    }

}
