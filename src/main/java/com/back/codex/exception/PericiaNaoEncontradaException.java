package com.back.codex.exception;

public class PericiaNaoEncontradaException extends RuntimeException {
    public PericiaNaoEncontradaException(Long id) {
        super("Perícia não encontrada com o id: " + id);
    }
}
