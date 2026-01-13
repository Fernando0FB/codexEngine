package com.back.codex.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ClasseResponse(
        Long id,
        String nome,
        BigDecimal nivel,
        List<EfeitoResponse> efeitos
) {
}
