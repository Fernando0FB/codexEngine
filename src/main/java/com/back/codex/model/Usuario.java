package com.back.codex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "tb_usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenant", type = String.class)})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O nome deve ser informado")
    private String nome;

    @Column(unique = true, name = "login", nullable = false)
    @NotBlank(message = "O login deve ser informado")
    private String login;

    @Column(name = "senha", nullable = false)
    @NotBlank(message = "A senha deve ser informada")
    private String senha;

    @Column(name = "identificador_unico")
    private String identificadorUnico;

    @PrePersist
    private void gerarIdentificadorUnico() {
        if (this.identificadorUnico == null) {
            this.identificadorUnico = java.util.UUID.randomUUID().toString();
        }
    }

}