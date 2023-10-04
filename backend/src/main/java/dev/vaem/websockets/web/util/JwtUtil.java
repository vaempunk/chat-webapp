package dev.vaem.websockets.web.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import dev.vaem.websockets.domain.entity.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final long expirationTimeMs;

    private final KeyPair keyPair;

    public JwtUtil() throws NoSuchAlgorithmException {
        expirationTimeMs = Duration.ofDays(1).toMillis();
        var generator = KeyPairGenerator.getInstance("RSA");
        var random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(0L);
        generator.initialize(2048, random);
        keyPair = generator.generateKeyPair();
    }

    public String generateToken(String sub, Role.Name role) {
        return Jwts.builder()
                .setSubject(sub)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs))
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public Optional<UsernamePasswordAuthenticationToken> parseToken(String token) {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(keyPair.getPublic())
                    .build()
                    .parseClaimsJws(token);
            var sub = claims
                    .getBody()
                    .getSubject();
            var role = claims
                    .getBody()
                    .get("role", String.class);
            return Optional.of(new UsernamePasswordAuthenticationToken(
                    Long.valueOf(sub), null,
                    Collections.singleton(new SimpleGrantedAuthority(RoleMapper.roleNameToAuthority(Role.Name.valueOf(role))))));
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }

    }

}
