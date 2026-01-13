package com.back.codex.mapper;

import com.back.codex.dto.request.ItemRequest;
import com.back.codex.dto.response.ItemResponse;
import com.back.codex.model.Item;
import com.back.codex.model.Personagem;

public class ItemMapper {

    public static ItemResponse toResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getNome(),
                item.getDescricao(),
                item.getQuantidade(),
                item.getPeso(),
                item.getEquipado(),
                item.getEfeitos()
                        .stream()
                        .map(EfeitoMapper::toResponse)
                        .toList()
        );
    }

    public static Item toEntity(ItemRequest itemRequest, Personagem personagem) {
        Item item = new Item();
        item.setNome(itemRequest.nome());
        item.setDescricao(itemRequest.descricao());
        item.setQuantidade(itemRequest.quantidade());
        item.setPeso(itemRequest.peso());
        item.setEquipado(itemRequest.equipado());
        item.setPersonagem(personagem);
        return item;
    }

}
