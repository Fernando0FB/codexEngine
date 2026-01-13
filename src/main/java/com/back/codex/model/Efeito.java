package com.back.codex.model;

import com.back.codex.config.tenancy.TenantBaseEntity;
import com.back.codex.enums.TipoOrigem;
import com.back.codex.enums.TipoEfeito;
import com.back.codex.enums.TipoModificacao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_efeitos", indexes = {
        @Index(name = "idx_efeito_origem", columnList = "origem_tipo, origem_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Efeito extends TenantBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "atributo_vinculado")
    private Atributo atributoVinculado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEfeito tipoEfeito;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_modificacao", nullable = false)
    private TipoModificacao tipoModificacao;

    @Column(nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem_tipo", nullable = false)
    private TipoOrigem tipoOrigem;

    @Column(name = "origem_id", nullable = false)
    private Long origemId;
}



