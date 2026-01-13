package com.back.codex.exception;

public class ClasseNaoEncontradaException extends RuntimeException {
    public ClasseNaoEncontradaException(Long id) {
        super("Classe não encontrada com o id: " + id);
    }
}
