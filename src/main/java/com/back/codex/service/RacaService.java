package com.back.codex.service;

import com.back.codex.dto.request.EfeitoRequest;
import com.back.codex.dto.request.RacaRequest;
import com.back.codex.dto.response.RacaResponse;
import com.back.codex.exception.RacaNaoEncontradaException;
import com.back.codex.mapper.RacaMapper;
import com.back.codex.model.Raca;
import com.back.codex.repository.RacaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RacaService {

    private final RacaRepository racaRepository;

    public RacaService(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    public Page<RacaResponse> findAll(Pageable pageable) {
        return racaRepository.findAll(pageable)
                .map(RacaMapper::toResponse);
    }

    public RacaResponse findById(Long id) {
        return racaRepository.findById(id)
                .map(RacaMapper::toResponse)
                .orElseThrow(() -> new RacaNaoEncontradaException(id));
    }

    public RacaResponse create(RacaRequest racaRequest) {
        Raca raca = RacaMapper.toEntity(racaRequest);
        raca = racaRepository.save(raca);
        return RacaMapper.toResponse(raca);
    }

    public void delete(Long id) {
        if (!racaRepository.existsById(id)) {
            throw new RacaNaoEncontradaException(id);
        }
        racaRepository.deleteById(id);
    }
}
