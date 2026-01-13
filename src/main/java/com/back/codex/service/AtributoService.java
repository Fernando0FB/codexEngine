package com.back.codex.service;

import com.back.codex.dto.request.AtributoRequest;
import com.back.codex.dto.response.AtributoResponse;
import com.back.codex.exception.AtributoNaoEncontradoException;
import com.back.codex.exception.PersonagemNaoEncontradoException;
import com.back.codex.mapper.AtributoMapper;
import com.back.codex.model.Atributo;
import com.back.codex.model.Personagem;
import com.back.codex.repository.AtributoRepository;
import com.back.codex.repository.PersonagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AtributoService {

    private final AtributoRepository atributoRepository;
    private final PersonagemRepository personagemRepository;

    public AtributoService(AtributoRepository atributoRepository, PersonagemRepository personagemRepository) {
        this.atributoRepository = atributoRepository;
        this.personagemRepository = personagemRepository;
    }

    public AtributoResponse findById(Long id) {
        return atributoRepository.findById(id)
                .map(AtributoMapper::toResponse)
                .orElseThrow(() -> new AtributoNaoEncontradoException(id));
    }

    public Page<AtributoResponse> findAll(Pageable pageable) {
        return atributoRepository.findAll(pageable)
                .map(AtributoMapper::toResponse);
    }

    @Transactional
    public AtributoResponse create(AtributoRequest atributoRequest) {
        Personagem personagem = personagemRepository.findById(atributoRequest.personagemId()).orElseThrow(
                () -> new PersonagemNaoEncontradoException(atributoRequest.personagemId())
        );

        Atributo atributo = AtributoMapper.toEntity(atributoRequest, personagem);
        atributo = atributoRepository.save(atributo);
        return AtributoMapper.toResponse(atributo);
    }

    @Transactional
    public AtributoResponse atualizarValorAtual(Long atributoId, BigDecimal novoValor) {
        Atributo atributo = atributoRepository.findById(atributoId)
                .orElseThrow(() -> new AtributoNaoEncontradoException(atributoId));

        atributo.setValorAtual(novoValor);
        atributo = atributoRepository.save(atributo);

        return AtributoMapper.toResponse(atributo);
    }

    @Transactional
    public void delete(Long id) {
        if (!atributoRepository.existsById(id)) {
            throw new AtributoNaoEncontradoException(id);
        }
        atributoRepository.deleteById(id);
    }
}
