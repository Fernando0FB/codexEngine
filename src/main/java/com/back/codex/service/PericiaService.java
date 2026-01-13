package com.back.codex.service;

import com.back.codex.dto.request.PericiaRequest;
import com.back.codex.dto.response.PericiaResponse;
import com.back.codex.exception.AtributoNaoEncontradoException;
import com.back.codex.exception.PericiaNaoEncontradaException;
import com.back.codex.mapper.PericiaMapper;
import com.back.codex.model.Atributo;
import com.back.codex.model.Pericia;
import com.back.codex.repository.AtributoRepository;
import com.back.codex.repository.PericiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PericiaService {

    private final PericiaRepository periciaRepository;
    private final AtributoRepository atributoRepository;

    public PericiaService(PericiaRepository periciaRepository, AtributoRepository atributoRepository) {
        this.periciaRepository = periciaRepository;
        this.atributoRepository = atributoRepository;
    }

    public Page<PericiaResponse> findAll(Pageable pageable) {
        return periciaRepository.findAll(pageable).map(PericiaMapper::toResponse);
    }

    public PericiaResponse findById(Long id) {
        return periciaRepository.findById(id).map(PericiaMapper::toResponse).orElseThrow(
                () -> new PericiaNaoEncontradaException(id)
        );
    }

    public PericiaResponse create(PericiaRequest request) {
        Atributo atributo = atributoRepository.findById(request.atributoBaseId()).orElseThrow(
                () -> new AtributoNaoEncontradoException(request.atributoBaseId())
        );

        Pericia pericia = PericiaMapper.toEntity(request, atributo);
        pericia = periciaRepository.save(pericia);
        return PericiaMapper.toResponse(pericia);
    }

    public void deleteById(Long id) {
        if (!periciaRepository.existsById(id)) {
            throw new PericiaNaoEncontradaException(id);
        }
        periciaRepository.deleteById(id);
    }

}
