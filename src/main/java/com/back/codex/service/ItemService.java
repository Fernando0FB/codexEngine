package com.back.codex.service;

import com.back.codex.dto.request.ItemRequest;
import com.back.codex.dto.response.ItemResponse;
import com.back.codex.exception.ItemNaoEncontradoException;
import com.back.codex.exception.PersonagemNaoEncontradoException;
import com.back.codex.mapper.ItemMapper;
import com.back.codex.model.Item;
import com.back.codex.model.Personagem;
import com.back.codex.repository.ItemRepository;
import com.back.codex.repository.PersonagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final PersonagemRepository personagemRepository;

    public ItemService(ItemRepository itemRepository, PersonagemRepository personagemRepository) {
        this.itemRepository = itemRepository;
        this.personagemRepository = personagemRepository;
    }

    public Page<ItemResponse> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable).map(ItemMapper::toResponse);
    }

    public ItemResponse findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException(id));
        return ItemMapper.toResponse(item);
    }

    public Page<ItemResponse> findByPersonagemId(Long personagemId, Pageable pageable) {
        return itemRepository.findByPersonagemId(personagemId, pageable)
                .map(ItemMapper::toResponse);
    }//TODO criar get com isso no controller de personagem

    public List<ItemResponse> buscarItensEquipados(Long personagemId) {
        if (!personagemRepository.existsById(personagemId)) {
            throw new PersonagemNaoEncontradoException(personagemId);
        }

        return itemRepository.findByPersonagemIdAndEquipadoTrue(personagemId).stream()
                .map(ItemMapper::toResponse)
                .toList();
    }//TODO criar get com isso no controller de personagem

    @Transactional
    public ItemResponse create(ItemRequest itemRequest) {
        Personagem personagem = personagemRepository.findById(itemRequest.personagemId()).orElseThrow(
                () -> new PersonagemNaoEncontradoException(itemRequest.personagemId())
        );

        Item item = ItemMapper.toEntity(itemRequest, personagem);
        item = itemRepository.save(item);
        return ItemMapper.toResponse(item);
    }

    @Transactional
    public ItemResponse equipar(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNaoEncontradoException(itemId));

        item.setEquipado(true);
        item = itemRepository.save(item);

        return ItemMapper.toResponse(item);
    }

    @Transactional
    public ItemResponse desequipar(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNaoEncontradoException(itemId));

        item.setEquipado(false);
        item = itemRepository.save(item);

        return ItemMapper.toResponse(item);
    }

    public void delete(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException(id));
        itemRepository.delete(item);
    }

}
