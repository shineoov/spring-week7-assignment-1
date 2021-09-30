package com.codesoom.assignment.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private final Key key;
    public JwtUtil(@Value("${jwt.secret}")  String secret ) {

        key = Keys.hmacShaKeyFor(secret.getBytes());

    }

    public String encode(Long userId) {

        String compact = Jwts.builder()
                .signWith(key)
                .claim("userId",userId)
                .compact();

        return compact;

    }

    public Claims decode(String token) {

         return Jwts.parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token)
                 .getBody();

    }

}

