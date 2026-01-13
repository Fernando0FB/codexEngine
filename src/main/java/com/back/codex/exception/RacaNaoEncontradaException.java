package com.back.codex.exception;

public class RacaNaoEncontradaException extends RuntimeException {
    public RacaNaoEncontradaException(Long id) {
        super("Raça não encontrada com o id: " + id);
    }
}
