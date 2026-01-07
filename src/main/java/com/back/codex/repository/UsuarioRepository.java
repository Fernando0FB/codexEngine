package com.back.codex.repository;

import com.back.codex.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findUsuariosByUsuario(String usuario);
}
