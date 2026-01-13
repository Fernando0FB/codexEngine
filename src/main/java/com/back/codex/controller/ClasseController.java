package com.back.codex.controller;

import com.back.codex.dto.request.ClasseRequest;
import com.back.codex.dto.response.ClasseResponse;
import com.back.codex.service.ClasseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public ResponseEntity<Page<ClasseResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(classeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClasseResponse> create(@Valid @RequestBody ClasseRequest classeRequest) {
        ClasseResponse response = classeService.create(classeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/novo-nivel")
    public ResponseEntity<ClasseResponse> novoNivel(@PathVariable Long id, @RequestParam("nivel") BigDecimal nivel) {
        ClasseResponse response = classeService.alterarNivel(id, nivel);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
