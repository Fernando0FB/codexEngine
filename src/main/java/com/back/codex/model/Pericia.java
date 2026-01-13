package com.back.codex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pericias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pericia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "atributo_base_id", nullable = false)
    private Atributo atributoBase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "personagem_id",
            nullable = false
    )
    private Personagem personagem;
}
