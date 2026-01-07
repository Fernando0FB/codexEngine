package com.back.codex.exception;

public abstract class EntidadeNaoEncontradaException extends RuntimeException {
  public EntidadeNaoEncontradaException(String message) {
    super(message);
  }
}