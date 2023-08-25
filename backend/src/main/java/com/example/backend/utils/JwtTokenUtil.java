package com.example.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private final Key secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    public JwtTokenUtil(@Value("${jwt.secret}") String secret) {
        //Generating a safe HS256 Secret key
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        this.secretKey = key;
    }

    // Generate JWT token
    public String generateToken(String username) {
        System.out.println("Generating token");

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username); // Adding username claim

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(issuedAt.getTime() + expiration);

        System.out.println("Issued At: " + issuedAt);
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("SECRET: " + secretKey);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        System.out.println("Generated Token: " + token);
        return token;
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date from JWT token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic method to extract claims from JWT token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from JWT token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Validate if JWT token is expired
    public boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }
}