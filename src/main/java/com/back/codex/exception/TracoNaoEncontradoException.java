package com.back.codex.exception;

public class TracoNaoEncontradoException extends RuntimeException {
    public TracoNaoEncontradoException(Long id) {
        super("Traço não encontrado com o id: " + id);
    }
}
