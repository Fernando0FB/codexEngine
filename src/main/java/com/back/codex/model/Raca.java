package com.back.codex.model;

import com.back.codex.config.tenancy.TenantBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "tb_racas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raca extends TenantBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O nome deve ser informado")
    private String nome;

    @OneToMany
    @JoinColumn(
            name = "origem_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    @SQLRestriction("origem_tipo = 'RACA'")
    private List<Efeito> efeitos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "personagem_id",
            nullable = false
    )
    private Personagem personagem;
}