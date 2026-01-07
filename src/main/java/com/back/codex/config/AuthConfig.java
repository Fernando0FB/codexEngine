package com.back.codex.config;

import com.back.codex.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService {

    private final UsuarioRepository userRepository;

    public AuthConfig(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUsuariosByUsuario(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}