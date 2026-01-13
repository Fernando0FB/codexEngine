package com.back.codex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_atributos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O nome deve ser informado")
    private String nome;

    @Column(name = "valor_base")
    private BigDecimal valorBase;

    @Column(name = "valor_atual")
    private BigDecimal valorAtual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "personagem_id",
            nullable = false
    )
    private Personagem personagem;

}
