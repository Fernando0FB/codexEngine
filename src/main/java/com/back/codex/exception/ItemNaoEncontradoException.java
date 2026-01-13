package com.back.codex.exception;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(Long id) {
        super("Item não encontrado com o id: " + id);
    }
}
