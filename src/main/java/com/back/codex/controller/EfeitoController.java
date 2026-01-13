package com.back.codex.controller;

import com.back.codex.dto.request.EfeitoRequest;
import com.back.codex.dto.response.EfeitoResponse;
import com.back.codex.enums.TipoOrigem;
import com.back.codex.service.EfeitoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/efeitos")
public class EfeitoController {

    private final EfeitoService efeitoService;

    public EfeitoController(EfeitoService efeitoService) {
        this.efeitoService = efeitoService;
    }

    @GetMapping
    public ResponseEntity<Page<EfeitoResponse>> findAll(
            @PageableDefault Pageable pageable,
            @Param("origem") TipoOrigem origem,
            @Param("idOrigem") Long idOrigem
    ) {
        return ResponseEntity.ok(efeitoService.buscarEfeitos(pageable, origem, idOrigem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EfeitoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(efeitoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EfeitoResponse> create(@Valid @RequestBody EfeitoRequest efeitoRequest) {
        EfeitoResponse response = efeitoService.create(efeitoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        efeitoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
