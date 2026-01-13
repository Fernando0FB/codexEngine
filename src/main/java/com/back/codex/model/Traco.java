package com.back.codex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "tb_traco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany
    @JoinColumn(
            name = "origem_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    @SQLRestriction("origem_tipo = 'TRACO'")
    private List<Efeito> efeitos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "personagem_id"
    )
    private Personagem personagem;
}
