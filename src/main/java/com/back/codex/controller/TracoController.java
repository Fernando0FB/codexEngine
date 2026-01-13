package com.back.codex.controller;

import com.back.codex.dto.request.TracoRequest;
import com.back.codex.dto.response.TracoResponse;
import com.back.codex.service.TracoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracos")
public class TracoController {

    private final TracoService tracoService;

    public TracoController(TracoService tracoService) {
        this.tracoService = tracoService;
    }

    @GetMapping
    public ResponseEntity<Page<TracoResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(tracoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TracoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tracoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TracoResponse> create(@Valid @RequestBody TracoRequest tracoRequest) {
        TracoResponse response = tracoService.create(tracoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tracoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
