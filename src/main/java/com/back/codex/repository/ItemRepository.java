package com.back.codex.repository;

import com.back.codex.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByPersonagemId(Long personagemId, Pageable pageable);

    List<Item> findByPersonagemIdAndEquipadoTrue(Long personagemId);
}
