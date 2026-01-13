package com.back.codex.exception;

public class EfeitoNaoEncontradoException extends RuntimeException {
    public EfeitoNaoEncontradoException(Long id) {
        super("Efeito não encontrado com o id: " + id);
    }
}
