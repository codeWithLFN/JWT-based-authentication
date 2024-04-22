package com.example.jwtbasedauthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtTokenUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Long expirationTime;

    public String generateToken(String username) {
        Claims claims = (Claims) Jwts.claims().setSubject(username);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            JwtParserBuilder jwtParserBuilder;
            jwtParserBuilder = Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey));
            JwtParser jwtParser = jwtParserBuilder.build();
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}