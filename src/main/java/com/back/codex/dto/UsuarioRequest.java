package com.back.codex.dto;


import com.back.codex.model.Usuario;

public record UsuarioRequest(String nome, String login, String senha, String cpf) {
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setCpf(cpf);
        return usuario;
    }
}