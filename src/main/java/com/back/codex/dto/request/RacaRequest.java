package com.back.codex.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RacaRequest(
        @NotBlank(message = "O nome deve ser informado")
        String nome
) {}
