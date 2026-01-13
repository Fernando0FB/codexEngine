package com.back.codex.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record PersonagemResponse(
        Long id,
        String nome,
        BigDecimal nivel,
        BigDecimal vidaMaxima,
        BigDecimal vidaAtual,
        BigDecimal sanidadeMaxima,
        BigDecimal sanidadeAtual,
        BigDecimal vontadeMaxima,
        BigDecimal vontadeAtual,
        List<AtributoResponse> atributos,
        List<ClasseResponse> classes,
        List<ItemResponse> itens,
        List<PericiaResponse> pericias,
        List<RacaResponse> raca,
        List<TracoResponse> traco
) {
}
