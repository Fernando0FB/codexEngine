package com.back.codex.service;

import com.back.codex.dto.request.TracoRequest;
import com.back.codex.dto.response.TracoResponse;
import com.back.codex.exception.TracoNaoEncontradoException;
import com.back.codex.mapper.TracoMapper;
import com.back.codex.model.Traco;
import com.back.codex.repository.TracoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TracoService {

    private final TracoRepository tracoRepository;

    public TracoService(TracoRepository tracoRepository) {
        this.tracoRepository = tracoRepository;
    }

    public Page<TracoResponse> findAll(Pageable pageable) {
        return tracoRepository.findAll(pageable).map(TracoMapper::toResponse);
    }

    public TracoResponse findById(Long id) {
        return tracoRepository.findById(id)
                .map(TracoMapper::toResponse)
                .orElseThrow(() -> new TracoNaoEncontradoException(id));
    }

    public TracoResponse create(TracoRequest tracoRequest) {
        Traco traco = TracoMapper.toEntity(tracoRequest);
        traco = tracoRepository.save(traco);
        return TracoMapper.toResponse(traco);
    }

    public void delete(Long id) {
        if (!tracoRepository.existsById(id)) {
            throw new TracoNaoEncontradoException(id);
        }
        tracoRepository.deleteById(id);
    }
}
