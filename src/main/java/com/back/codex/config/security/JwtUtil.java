package com.back.codex.config.security;

import com.back.codex.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;
    @Value("${app.jwt.expiration}")
    private long EXPIRATION_TIME;

    private SecretKey getSigningKey() {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
        return secretKey;
    }

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))
                .setIssuedAt(new Date())
                .claim("tenant", usuario.getIdentificadorUnico())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractTenantId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("tenant", String.class);
    }
}
