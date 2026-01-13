package com.back.codex.exception;

public class TipoOrigemInvalidoException extends RuntimeException {
    public TipoOrigemInvalidoException(String descricao) {
        super("Não foi possível encontrar um tipo origem com a descrição: " + descricao);
    }
}
