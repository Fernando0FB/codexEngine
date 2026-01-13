package com.back.codex.enums;

public enum TipoOrigem {
    ITEM("Item"),
    CLASSE("Classe"),
    RACA("Raça"),
    TRACO("Traço");

    private final String descricao;

    TipoOrigem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
