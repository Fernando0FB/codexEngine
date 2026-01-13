package com.back.codex.enums;

public enum TipoModificacao {
    DADOS("Dados"),
    BONUS("Bônus"),
    PERCENTUAL("Percentual");

    private final String descricao;

    TipoModificacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
