package com.back.codex.service;

import com.back.codex.dto.request.EfeitoRequest;
import com.back.codex.dto.response.EfeitoResponse;
import com.back.codex.enums.TipoOrigem;
import com.back.codex.exception.*;
import com.back.codex.mapper.EfeitoMapper;
import com.back.codex.model.Atributo;
import com.back.codex.model.Classe;
import com.back.codex.model.Efeito;
import com.back.codex.model.Item;
import com.back.codex.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EfeitoService {

    private final EfeitoRepository efeitoRepository;
    private final AtributoRepository atributoRepository;
    private final ItemRepository itemRepository;
    private final ClasseRepository classeRepository;
    private final RacaRepository racaRepository;
    private final TracoRepository tracoRepository;

    public EfeitoService(EfeitoRepository efeitoRepository, AtributoRepository atributoRepository, ItemRepository itemRepository, ClasseRepository classeRepository, RacaRepository racaRepository, TracoRepository tracoRepository) {
        this.efeitoRepository = efeitoRepository;
        this.atributoRepository = atributoRepository;
        this.itemRepository = itemRepository;
        this.classeRepository = classeRepository;
        this.racaRepository = racaRepository;
        this.tracoRepository = tracoRepository;
    }

    public Page<EfeitoResponse> buscarEfeitos(Pageable pageable, TipoOrigem origem, Long idOrigem) {
        return efeitoRepository.findByTipoOrigemAndOrigemId(pageable, origem, idOrigem)
                .map(EfeitoMapper::toResponse);
    }

    public EfeitoResponse findById(Long id) {
        Efeito efeito = efeitoRepository.findById(id)
                .orElseThrow(() -> new EfeitoNaoEncontradoException(id));
        return EfeitoMapper.toResponse(efeito);
    }

    @Transactional
    public EfeitoResponse create(EfeitoRequest efeitoRequest) {
        Atributo atributo = atributoRepository.findById(efeitoRequest.atributoVinculadoId()).orElseThrow(
                () -> new AtributoNaoEncontradoException(efeitoRequest.atributoVinculadoId())
        );

        Efeito efeito = EfeitoMapper.toEntity(efeitoRequest, atributo);
        Long origemId = efeitoRequest.origemId();

        switch (efeitoRequest.tipoOrigem()) {
            case ITEM -> {
                if (!itemRepository.existsById(origemId)) {
                    throw new ItemNaoEncontradoException(origemId);
                }
                efeito.setTipoOrigem(TipoOrigem.ITEM);
            }
            case CLASSE -> {
                if (!classeRepository.existsById(origemId)) {
                    throw new ClasseNaoEncontradaException(origemId);
                }
                efeito.setTipoOrigem(TipoOrigem.CLASSE);
            }
            case RACA -> {
                if (!racaRepository.existsById(origemId)) {
                    throw new RacaNaoEncontradaException(origemId);
                }
                efeito.setTipoOrigem(TipoOrigem.RACA);
            }
            case TRACO -> {
                if (!tracoRepository.existsById(origemId)) {
                    throw new TracoNaoEncontradoException(origemId);
                }
                efeito.setTipoOrigem(TipoOrigem.TRACO);
            }
            default -> throw new TipoOrigemInvalidoException(efeitoRequest.tipoOrigem().getDescricao());
        }
        efeito.setOrigemId(origemId);
        efeito = efeitoRepository.save(efeito);
        return EfeitoMapper.toResponse(efeito);
    }

    @Transactional
    public void delete(Long id) {
        if (!efeitoRepository.existsById(id)) {
            throw new EfeitoNaoEncontradoException(id);
        }
        efeitoRepository.deleteById(id);
    }
}