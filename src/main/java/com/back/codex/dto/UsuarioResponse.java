package com.back.codex.dto;


import com.back.codex.model.Usuario;

public record UsuarioResponse(Long id, String nome, String login, String cpf) {
    public static UsuarioResponse from(Usuario u) { return new UsuarioResponse(u.getId() ,u.getNome() ,u.getLogin(),u.getCpf() );}
}
