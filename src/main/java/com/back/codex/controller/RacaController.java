package com.back.codex.controller;

import com.back.codex.dto.request.RacaRequest;
import com.back.codex.dto.response.RacaResponse;
import com.back.codex.service.RacaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/racas")
public class RacaController {

    private final RacaService racaService;

    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    @GetMapping
    public ResponseEntity<Page<RacaResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(racaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RacaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(racaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RacaResponse> create(@Valid @RequestBody RacaRequest racaRequest) {
        RacaResponse response = racaService.create(racaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        racaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
