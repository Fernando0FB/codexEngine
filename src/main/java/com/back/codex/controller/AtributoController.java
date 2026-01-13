package com.back.codex.controller;

import com.back.codex.dto.request.AtributoRequest;
import com.back.codex.dto.response.AtributoResponse;
import com.back.codex.service.AtributoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/atributos")
public class AtributoController {

    private final AtributoService atributoService;

    public AtributoController(AtributoService atributoService) {
        this.atributoService = atributoService;
    }

    @GetMapping
    public ResponseEntity<Page<AtributoResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(atributoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtributoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(atributoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AtributoResponse> create(@Valid @RequestBody AtributoRequest atributoRequest) {
        AtributoResponse createdAtributo = atributoService.create(atributoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAtributo);
    }

    @PostMapping("/{id}/atualizar-valor")
    public ResponseEntity<AtributoResponse> atualizarValorAtual(@PathVariable Long id, @RequestParam BigDecimal novoValor) {
        AtributoResponse atributoAtualizado = atributoService.atualizarValorAtual(id, novoValor);
        return ResponseEntity.status(HttpStatus.CREATED).body(atributoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atributoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
