package com.avanade.avanade.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String getToken(String username){
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataExpiracao = dataAtual.plusMinutes(30);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(dataAtual.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .setExpiration(new Date(dataExpiracao.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .signWith(secretKey)
                .compact();
    }

    public String getUserNameByToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .isSigned(token);
    }
}
