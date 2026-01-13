package com.back.codex.dto.response;

import java.util.List;

public record RacaResponse(
        String nome,
        List<EfeitoResponse> efeitos
) {
}
