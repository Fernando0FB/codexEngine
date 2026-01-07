package com.back.codex.dto.request;

import com.back.codex.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioPostRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 80, message = "Nome deve ter no máximo 80 caracteres")
        String nome,

        @NotBlank(message = "Usuário é obrigatório")
        @Size(max = 80, message = "Usuário deve ter no máximo 80 caracteres")
        String usuario,

        @NotBlank(message = "Senha é obrigatória")
        @Size(max = 80, message = "Senha deve ter no máximo 80 caracteres")
        String senha,

        Role role
) {}