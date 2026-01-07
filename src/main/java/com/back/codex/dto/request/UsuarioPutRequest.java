package com.back.codex.dto.request;

import com.back.codex.enums.Role;
import jakarta.validation.constraints.Size;

public record UsuarioPutRequest(
        @Size(max = 80, message = "Nome deve ter no máximo 80 caracteres")
        String nome,

        @Size(max = 80, message = "Usuário deve ter no máximo 80 caracteres")
        String usuario,

        Role role
) {
}