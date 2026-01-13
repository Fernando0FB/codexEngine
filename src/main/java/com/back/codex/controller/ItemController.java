package com.back.codex.controller;

import com.back.codex.dto.request.EfeitoRequest;
import com.back.codex.dto.request.ItemRequest;
import com.back.codex.dto.response.EfeitoResponse;
import com.back.codex.dto.response.ItemResponse;
import com.back.codex.service.EfeitoService;
import com.back.codex.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    private final ItemService itemService;
    private final EfeitoService efeitoService;

    public ItemController(ItemService itemService, EfeitoService efeitoService) {
        this.itemService = itemService;
        this.efeitoService = efeitoService;
    }

    @GetMapping
    public ResponseEntity<Page<ItemResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(itemService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ItemResponse> create(@Valid @RequestBody ItemRequest itemRequest) {
        ItemResponse response = itemService.create(itemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("{id}/efeitos")
    public ResponseEntity<EfeitoResponse> createEfeito(@Valid @RequestBody EfeitoRequest efeitoRequest) {
        EfeitoResponse response = efeitoService.create(efeitoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/{id}/equipar")
    public ResponseEntity<ItemResponse> equipar(@PathVariable Long id) {
        ItemResponse response = itemService.equipar(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}/desequipar")
    public ResponseEntity<ItemResponse> desequipar(@PathVariable Long id) {
        ItemResponse response = itemService.desequipar(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
