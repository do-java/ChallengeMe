package com.dj.cm.biz.service.token.impl;

import com.dj.cm.biz.service.token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;
import static java.util.Objects.requireNonNull;

@Service
public class JWTTokenService implements TokenService {
    private String issuer;
    private int expirationSec;
    private int clockSkewSec;
    private String secretKey;

    JWTTokenService(@Value("${jwt.issuer:cm}") String issuer,
                    @Value("${jwt.expiration-sec:86400}") int expirationSec,
                    @Value("${jwt.clock-skew-sec:300}") int clockSkewSec,
                    @Value("${jwt.secret:secret}") String secret) {
        super();
        this.issuer = requireNonNull(issuer);
        this.expirationSec = requireNonNull(expirationSec);
        this.clockSkewSec = requireNonNull(clockSkewSec);
        this.secretKey = BASE64.encode(requireNonNull(secret));
    }

    @Override
    public String generatePermanentToken(Map<String, String> attributes) {
        return newToken(attributes, 0);
    }

    @Override
    public String generateExpiringToken(Map<String, String> attributes) {
        return newToken(attributes, expirationSec);
    }

    private String newToken(Map<String, String> attributes, int expiresInSec) {
        Instant instant = Instant.now();

        Claims claims = Jwts
                .claims()
                .setIssuer(issuer)
                .setIssuedAt(Date.from(instant));

        if (expiresInSec > 0) {
            Instant expiresAt = instant.plus(Duration.ofSeconds(expiresInSec));
            claims.setExpiration(Date.from(expiresAt));
        }
        claims.putAll(attributes);

        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(HS256, secretKey)
                .compressWith(new GzipCompressionCodec())
                .compact();
    }

    @Override
    public Map<String, String> verifyToken(String token) {
        JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setAllowedClockSkewSeconds(clockSkewSec)
                .setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    private Map<String, String> parseClaims(Supplier<Claims> toClaims) {
        Map<String, String> claimsAll = new HashMap<>();

        try {
            Claims claims = toClaims.get();

            for (Map.Entry<String, Object> e: claims.entrySet()) {
                claimsAll.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return claimsAll;
        } catch (IllegalArgumentException | JwtException e) {
            return claimsAll;
        }
    }
}