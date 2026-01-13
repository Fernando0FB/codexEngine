package com.back.codex.mapper;

import com.back.codex.dto.request.ClasseRequest;
import com.back.codex.dto.response.ClasseResponse;
import com.back.codex.model.Classe;
import com.back.codex.model.Personagem;

public class ClasseMapper {

    public static ClasseResponse toResponse(Classe classe) {
        return new ClasseResponse(
                classe.getId(),
                classe.getNome(),
                classe.getNivel(),
                classe.getEfeitos().stream()
                        .map(EfeitoMapper::toResponse)
                        .toList()
        );
    }

    public static Classe toEntity(ClasseRequest classeRequest, Personagem personagem) {
        Classe classe = new Classe();
        classe.setNome(classeRequest.nome());
        classe.setNivel(classeRequest.nivel());
        classe.setPersonagem(personagem);
        return classe;
    }
}
