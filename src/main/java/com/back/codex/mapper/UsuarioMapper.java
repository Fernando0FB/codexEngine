package com.back.codex.mapper;

import com.back.codex.dto.request.UsuarioRequest;
import com.back.codex.dto.response.UsuarioResponse;
import com.back.codex.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario usuario){
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin()
        );
    }

    public static Usuario toEntity(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.nome());
        usuario.setLogin(usuarioRequest.login());
        usuario.setSenha(usuarioRequest.senha());
        return usuario;
    }

}
