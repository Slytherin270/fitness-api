package com.fitnessapp.auth.application;

import com.fitnessapp.auth.domain.UserAccount;
import java.time.Instant;
import java.util.Locale;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthUseCase {

    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public AuthUseCase(AuthRepository repository, PasswordEncoder passwordEncoder, JwtTokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Transactional
    public AuthResult register(String email, String rawPassword, String displayName) {
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        repository.findByEmail(normalizedEmail).ifPresent(user -> {
            throw new IllegalArgumentException("Email already in use");
        });

        UserAccount userAccount = repository.save(new UserAccount(
                UUID.randomUUID(),
                normalizedEmail,
                passwordEncoder.encode(rawPassword),
                displayName.trim(),
                Instant.now()));

        return new AuthResult(userAccount, tokenService.issueToken(userAccount));
    }

    @Transactional(readOnly = true)
    public AuthResult login(String email, String rawPassword) {
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        UserAccount userAccount = repository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(rawPassword, userAccount.passwordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return new AuthResult(userAccount, tokenService.issueToken(userAccount));
    }

    public record AuthResult(UserAccount userAccount, String token) {}
}
