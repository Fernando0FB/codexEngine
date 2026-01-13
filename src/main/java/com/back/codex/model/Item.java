package com.back.codex.model;

import com.back.codex.config.tenancy.TenantBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "itens")
@Getter
@Setter
public class Item extends TenantBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal quantidade;
    private BigDecimal peso;
    private Boolean equipado;

    @OneToMany
    @JoinColumn(
            name = "origem_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    @SQLRestriction("origem_tipo = 'ITEM'")
    private List<Efeito> efeitos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "personagem_id",
            nullable = false
    )
    private Personagem personagem;
}