package com.back.codex.mapper;

import com.back.codex.dto.request.AtributoRequest;
import com.back.codex.dto.response.AtributoResponse;
import com.back.codex.model.Atributo;
import com.back.codex.model.Personagem;

public class AtributoMapper {

    public static AtributoResponse toResponse(Atributo atributo){
        return new AtributoResponse(
                atributo.getId(),
                atributo.getNome(),
                atributo.getValorBase(),
                atributo.getValorAtual()
        );
    }

    public static Atributo toEntity(AtributoRequest atributoRequest, Personagem personagem){
        Atributo atributo = new Atributo();
        atributo.setNome(atributoRequest.nome());
        atributo.setValorBase(atributoRequest.valorBase());
        atributo.setValorAtual(atributoRequest.valorAtual());
        atributo.setPersonagem(personagem);
        return atributo;
    }
}
