package com.back.codex.service;


import com.back.codex.config.security.JwtUtil;
import com.back.codex.dto.LoginRequest;
import com.back.codex.dto.LoginResponse;
import com.back.codex.dto.UsuarioRequest;
import com.back.codex.dto.UsuarioResponse;
import com.back.codex.exception.SenhaInvalidaException;
import com.back.codex.exception.UsuarioNaoEncontradoException;
import com.back.codex.model.Usuario;
import com.back.codex.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByLogin(loginRequest.login().toLowerCase())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(loginRequest.login()));

        if (!passwordEncoder.matches(loginRequest.senha(), usuario.getSenha())) {
            throw new SenhaInvalidaException(loginRequest.login());
        }

        return new LoginResponse(jwtUtil.generateToken(usuario));
    }

    public UsuarioResponse cadastrarUsuario(UsuarioRequest usuarioReq) {
        Usuario usuario = usuarioReq.toEntity();
        usuario.setLogin(usuarioReq.login().toLowerCase());
        usuario.setSenha(passwordEncoder.encode(usuarioReq.senha()));
        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream().map(UsuarioResponse::from).toList();
    }
}
