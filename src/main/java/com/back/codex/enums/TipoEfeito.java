package com.back.codex.enums;

public enum TipoEfeito {
    BUFF("Buff"),
    DEBUFF("Debuff");

    private final String descricao;

    TipoEfeito(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
