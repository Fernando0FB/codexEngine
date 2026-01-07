package com.back.codex.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String login) {
        super("Senha inválida para o usuário: " + login);
    }
}
