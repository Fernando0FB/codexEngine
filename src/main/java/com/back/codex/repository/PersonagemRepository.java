package com.back.codex.repository;

import com.back.codex.model.Personagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Page<Personagem> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}