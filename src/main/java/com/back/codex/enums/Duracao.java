package com.back.codex.enums;

public enum Duracao {
    RODADA("Rodada"),
    SEGUNDOS("Segundos"),
    MINUTO("Minuto"),
    HORA("Hora"),
    DIA("Dia"),
    PERMANENTE("Permanente");

    private final String descricao;

    Duracao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
