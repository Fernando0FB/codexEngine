package com.back.codex.service;

import com.back.codex.dto.request.PersonagemRequest;
import com.back.codex.dto.response.PersonagemResponse;
import com.back.codex.exception.PersonagemNaoEncontradoException;
import com.back.codex.mapper.PersonagemMapper;
import com.back.codex.model.Personagem;
import com.back.codex.repository.PersonagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;
    }

    public PersonagemResponse findById(Long id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new PersonagemNaoEncontradoException(id));
        return PersonagemMapper.toResponse(personagem);
    }

    public Page<PersonagemResponse> findAll(Pageable pageable, String descricao) {
        if (descricao == null) {
            return personagemRepository.findAll(pageable)
                    .map(PersonagemMapper::toResponse);
        }

        return personagemRepository.findByNomeContainingIgnoreCase(descricao, pageable)
                .map(PersonagemMapper::toResponse);
    }

    @Transactional
    public PersonagemResponse create(PersonagemRequest personagemRequest) {
        Personagem personagem = PersonagemMapper.toEntity(personagemRequest);
        personagem = personagemRepository.save(personagem);
        return PersonagemMapper.toResponse(personagem);
    }

    @Transactional
    public void delete(Long id) {
        if (!personagemRepository.existsById(id)) {
            throw new PersonagemNaoEncontradoException(id);
        }
        personagemRepository.deleteById(id);
    }
}
