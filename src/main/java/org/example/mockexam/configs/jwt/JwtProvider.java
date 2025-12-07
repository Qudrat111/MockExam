package org.example.mockexam.configs.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.mockexam.modules.dto.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.accessTokenExpire}")
    private Integer accessTokenExpire;

    public TokenResponse generateAccessToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + accessTokenExpire * 24 * 60 * 60 * 1000L);

        String token = Jwts.builder()
                .issuer("mock-exam")
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(getSecretKey())
                .compact();

        return new TokenResponse(token);
    }

    public Boolean isValidated(String token) {
        try {
            Claims claims = (Claims) Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parse(token)
                    .getPayload();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        try {
            Claims payload = (Claims) Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parse(token)
                    .getPayload();
            return payload.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
