package com.back.codex.exception;

public class AtributoNaoEncontradoException extends RuntimeException {
    public AtributoNaoEncontradoException(Long id) {
        super("Atributo não encontrado com o id: " + id);
    }
}
