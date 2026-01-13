package com.back.codex.controller;

import com.back.codex.dto.request.PericiaRequest;
import com.back.codex.dto.response.PericiaResponse;
import com.back.codex.service.PericiaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pericias")
public class PericiaController {

    private final PericiaService periciaService;

    public PericiaController(PericiaService periciaService) {
        this.periciaService = periciaService;
    }

    @GetMapping
    public ResponseEntity<Page<PericiaResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(periciaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PericiaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(periciaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PericiaResponse> create(@Valid @RequestBody PericiaRequest periciaRequest) {
        PericiaResponse response = periciaService.create(periciaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        periciaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
