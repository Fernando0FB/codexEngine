package com.back.codex.controller;

import com.back.codex.dto.request.PersonagemRequest;
import com.back.codex.dto.response.PersonagemResponse;
import com.back.codex.service.PersonagemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {

    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @GetMapping
    public ResponseEntity<Page<PersonagemResponse>> findAll(
            @PageableDefault Pageable pageable,
            @RequestParam (required = false) String descricao
    ) {
        return ResponseEntity.ok(personagemService.findAll(pageable, descricao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonagemResponse> create(@RequestBody PersonagemRequest personagemRequest) {
        PersonagemResponse response = personagemService.create(personagemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
