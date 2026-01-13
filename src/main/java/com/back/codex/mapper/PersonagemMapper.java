package com.back.codex.mapper;

import com.back.codex.dto.request.PersonagemRequest;
import com.back.codex.dto.response.PersonagemResponse;
import com.back.codex.model.Personagem;

public class PersonagemMapper {

    public static PersonagemResponse toResponse(Personagem personagem) {
        return new PersonagemResponse(
                personagem.getId(),
                personagem.getNome(),
                personagem.getNivel(),
                personagem.getVidaMaxima(),
                personagem.getVidaAtual(),
                personagem.getSanidadeMaxima(),
                personagem.getSanidadeAtual(),
                personagem.getVontadeMaxima(),
                personagem.getVontadeAtual(),
                personagem.getAtributos().stream().map(
                        AtributoMapper::toResponse
                ).toList(),
                personagem.getClasses().stream().map(
                        ClasseMapper::toResponse
                ).toList(),
                personagem.getItens().stream().map(
                        ItemMapper::toResponse
                ).toList(),
                personagem.getPericias().stream().map(
                        PericiaMapper::toResponse
                ).toList(),
                personagem.getRaca().stream().map(
                        RacaMapper::toResponse
                ).toList(),
                personagem.getTraco().stream().map(
                        TracoMapper::toResponse
                ).toList()
        );
    }

    public static Personagem toEntity(PersonagemRequest personagemRequest) {
        Personagem personagem = new Personagem();
        personagem.setNome(personagemRequest.nome());
        personagem.setNivel(personagemRequest.nivel());
        personagem.setVidaMaxima(personagemRequest.vidaMaxima());
        personagem.setVidaAtual(personagemRequest.vidaAtual());
        personagem.setSanidadeMaxima(personagemRequest.sanidadeMaxima());
        personagem.setSanidadeAtual(personagemRequest.sanidadeAtual());
        personagem.setVontadeAtual(personagemRequest.vontadeAtual());
        personagem.setVontadeMaxima(personagemRequest.vontadeMaxima());
        return personagem;
    }
}
