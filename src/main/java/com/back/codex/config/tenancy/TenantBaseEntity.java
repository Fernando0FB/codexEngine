package com.back.codex.config.tenancy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@FilterDef(name = "tenantFilter", parameters = @ParamDef(name = "tenant", type = String.class))
@Filter(name = "tenantFilter", condition = "tenant = :tenant")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class TenantBaseEntity {
    
    @TenantField
    @Column(name = "tenant", nullable = false, updatable = false)//Referencia o Identificador Único do usuário
    private String tenant;
    
    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;
    
    @LastModifiedDate
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}