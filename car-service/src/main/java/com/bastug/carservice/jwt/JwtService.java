package com.bastug.carservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${secret}")
    private String secret;

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(
                secret.getBytes()
        );
    }

    public String extractUsername(String token) {

        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token) {

        Date expirationDate= Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return !expirationDate.before(new Date());
    }
}
