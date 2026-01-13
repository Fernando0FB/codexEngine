package com.back.codex.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
        @NotBlank(message = "O 'login' não pode ser vazio")
        String login,
        @NotBlank(message = "A 'senha' não pode ser vazia")
        String senha
) {}
