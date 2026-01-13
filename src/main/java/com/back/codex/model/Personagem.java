package com.back.codex.model;

import com.back.codex.config.tenancy.TenantBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_personagens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personagem extends TenantBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O nome deve ser informado")
    private String nome;

    @Column(name = "nivel")
    private BigDecimal nivel;

    @Column(name = "vida_maxima", nullable = false)
    @NotNull(message = "A vida máxima deve ser informada")
    private BigDecimal vidaMaxima;

    @Column(name = "vida_atual", nullable = false)
    @NotNull(message = "A vida máxima deve ser informada")
    private BigDecimal vidaAtual;

    @Column(name = "sanidade_maxima")
    private BigDecimal sanidadeMaxima;

    @Column(name = "sanidade_atual")
    private BigDecimal sanidadeAtual;

    @Column(name = "vontade_maxima")
    private BigDecimal vontadeMaxima;

    @Column(name = "vontade_atual")
    private BigDecimal vontadeAtual;

    @OneToMany(
            mappedBy = "personagem",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Atributo> atributos = new ArrayList<>();

    @OneToMany(
            mappedBy = "personagem",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Classe> classes = new ArrayList<>();

    @OneToMany(
            mappedBy = "personagem",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Item> itens = new ArrayList<>();

    @OneToMany(
            mappedBy = "personagem",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Pericia> pericias = new ArrayList<>();

    @OneToMany(
            mappedBy = "personagem",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Raca> raca = new ArrayList<>();

    @OneToMany(
            mappedBy = "personagem",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Traco> traco = new ArrayList<>();

}
