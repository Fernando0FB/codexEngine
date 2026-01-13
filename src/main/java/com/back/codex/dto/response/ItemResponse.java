package com.back.codex.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ItemResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal quantidade,
        BigDecimal peso,
        Boolean equipado,
        List<EfeitoResponse> efeitos
) {

}
