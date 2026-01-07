package com.back.codex.exception;

public class ErroConfirmacaoSenhaException extends RuntimeException {
    public ErroConfirmacaoSenhaException() {
        super("As senhas não conferem.");
    }
}
