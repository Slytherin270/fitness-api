package com.fitnessapp.auth.infrastructure;

import com.fitnessapp.auth.application.JwtTokenService;
import com.fitnessapp.auth.domain.UserAccount;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JjwtTokenService implements JwtTokenService {

    private final SecretKey secretKey;

    public JjwtTokenService(@Value("${security.jwt.secret:super-secret-signing-key-change-me}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String issueToken(UserAccount userAccount) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(userAccount.id().toString())
                .claim("email", userAccount.email())
                .claim("name", userAccount.displayName())
                .claim("scope", "ROLE_USER")
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(60L * 60L * 24L)))
                .signWith(secretKey)
                .compact();
    }
}
