package com.back.codex.mapper;

import com.back.codex.dto.request.EfeitoRequest;
import com.back.codex.dto.response.EfeitoResponse;
import com.back.codex.model.Atributo;
import com.back.codex.model.Efeito;

public class EfeitoMapper {

    public static EfeitoResponse toResponse(Efeito efeito) {
        return new EfeitoResponse(
                efeito.getDescricao(),
                AtributoMapper.toResponse(efeito.getAtributoVinculado()),
                efeito.getTipoEfeito().name(),
                efeito.getTipoModificacao().name(),
                efeito.getQuantidade()
        );
    }

    public static Efeito toEntity(EfeitoRequest efeitoRequest, Atributo atributo) {
        Efeito efeito = new Efeito();
        efeito.setDescricao(efeitoRequest.descricao());
        efeito.setAtributoVinculado(atributo);
        efeito.setTipoEfeito(efeitoRequest.tipoEfeito());
        efeito.setTipoModificacao(efeitoRequest.tipoModificacao());
        efeito.setQuantidade(efeitoRequest.quantidade());
        return efeito;
    }

}
