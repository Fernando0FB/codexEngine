package com.back.codex.repository;

import com.back.codex.enums.TipoOrigem;
import com.back.codex.enums.TipoEfeito;
import com.back.codex.model.Efeito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EfeitoRepository extends JpaRepository<Efeito, Long> {

    Page<Efeito> findByTipoOrigemAndOrigemId(Pageable page, TipoOrigem tipoOrigem, Long origemId);
    List<Efeito> findByAtributoVinculado(String atributo);
    List<Efeito> findByTipoEfeito(TipoEfeito tipoEfeito);

    @Query("""
        SELECT e FROM Efeito e 
        WHERE (e.tipoOrigem = :tipo1 AND e.origemId = :id1)
           OR (e.tipoOrigem = :tipo2 AND e.origemId = :id2)
        """)
    List<Efeito> findByMultiplasOrigens(
            @Param("tipo1") TipoOrigem tipo1, @Param("id1") Long id1,
            @Param("tipo2") TipoOrigem tipo2, @Param("id2") Long id2
    );
}