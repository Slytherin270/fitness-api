package com.fitnessapp.auth.infrastructure;

import com.fitnessapp.auth.application.AuthRepository;
import com.fitnessapp.auth.domain.UserAccount;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class AuthRepositoryAdapter implements AuthRepository {

    private final SpringDataUserAccountRepository repository;

    public AuthRepositoryAdapter(SpringDataUserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return repository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        UserAccountJpaEntity entity = new UserAccountJpaEntity();
        entity.setId(userAccount.id());
        entity.setEmail(userAccount.email());
        entity.setPasswordHash(userAccount.passwordHash());
        entity.setDisplayName(userAccount.displayName());
        entity.setCreatedAt(userAccount.createdAt());
        return toDomain(repository.save(entity));
    }

    private UserAccount toDomain(UserAccountJpaEntity entity) {
        return new UserAccount(entity.getId(), entity.getEmail(), entity.getPasswordHash(), entity.getDisplayName(), entity.getCreatedAt());
    }
}
