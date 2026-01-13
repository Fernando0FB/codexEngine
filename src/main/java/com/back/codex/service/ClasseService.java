package com.back.codex.service;

import com.back.codex.dto.request.ClasseRequest;
import com.back.codex.dto.response.ClasseResponse;
import com.back.codex.exception.ClasseNaoEncontradaException;
import com.back.codex.exception.PersonagemNaoEncontradoException;
import com.back.codex.mapper.ClasseMapper;
import com.back.codex.model.Classe;
import com.back.codex.model.Personagem;
import com.back.codex.repository.ClasseRepository;
import com.back.codex.repository.PersonagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;
    private final PersonagemRepository personagemRepository;

    public ClasseService(ClasseRepository classeRepository, PersonagemRepository personagemRepository) {
        this.classeRepository = classeRepository;
        this.personagemRepository = personagemRepository;
    }

    public Page<ClasseResponse> findAll(Pageable pageable) {
        return classeRepository.findAll(pageable)
                .map(ClasseMapper::toResponse);
    }

    public ClasseResponse findById(Long id) {
        return classeRepository.findById(id)
                .map(ClasseMapper::toResponse)
                .orElseThrow(() -> new ClasseNaoEncontradaException(id));
    }

    @Transactional
    public ClasseResponse create(ClasseRequest classeRequest) {
        Personagem personagem = personagemRepository.findById(classeRequest.personagemId()).orElseThrow(
                () -> new PersonagemNaoEncontradoException(classeRequest.personagemId())
        );

        Classe classe = ClasseMapper.toEntity(classeRequest, personagem);
        classe = classeRepository.save(classe);
        return ClasseMapper.toResponse(classe);
    }

    @Transactional
    public ClasseResponse alterarNivel(Long id, BigDecimal novoNivel) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new ClasseNaoEncontradaException(id));

        classe.setNivel(novoNivel);
        classe = classeRepository.save(classe);
        return ClasseMapper.toResponse(classe);
    }

    @Transactional
    public void delete(Long id) {
        if (!classeRepository.existsById(id)) {
            throw new ClasseNaoEncontradaException(id);
        }
        classeRepository.deleteById(id);
    }
}
