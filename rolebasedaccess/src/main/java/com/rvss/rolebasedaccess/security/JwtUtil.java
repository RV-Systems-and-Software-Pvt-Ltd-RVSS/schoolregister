package com.rvss.rolebasedaccess.security;

import com.rvss.rolebasedaccess.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Expose key safely via getter for use in JwtFilter
    public Key getKey() {
        return key;
    }

    public String generateToken(User user) {
        String roles = user.getRoles().stream()
                .map(r -> "ROLE_" + r.getName())  // ✅ FIX
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", roles)
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
