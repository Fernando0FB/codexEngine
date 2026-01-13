package com.back.codex.dto.response;

import java.util.List;

public record TracoResponse(
        Long id,
        String nome,
        List<EfeitoResponse> efeitos
) {
}
