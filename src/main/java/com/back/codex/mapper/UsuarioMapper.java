package com.back.codex.mapper;

import com.back.codex.dto.request.UsuarioPostRequest;
import com.back.codex.dto.request.UsuarioPutRequest;
import com.back.codex.dto.response.UsuarioResponse;
import com.back.codex.model.Usuarios;

public class UsuarioMapper {

    public static Usuarios toEntity(UsuarioPostRequest request) {
        return Usuarios.builder()
                .nome(request.nome())
                .senha(request.senha())
                .usuario(request.usuario())
                .build();
    }

    public static UsuarioResponse toResponse(Usuarios usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getUsuario()
        );
    }

    public static void merge(Usuarios entity, UsuarioPutRequest req) {
        if (req.nome() != null) entity.setNome(req.nome());
        if (req.usuario() != null) entity.setUsuario(req.usuario());
    }

}
