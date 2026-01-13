package com.back.codex.dto.response;

import java.math.BigDecimal;

public record AtributoResponse(
        Long id,
        String nome,
        BigDecimal valorBase,
        BigDecimal valorAtual
) {
}
