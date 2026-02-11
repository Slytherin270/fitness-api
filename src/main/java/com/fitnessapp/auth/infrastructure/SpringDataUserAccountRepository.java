package com.fitnessapp.auth.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserAccountRepository extends JpaRepository<UserAccountJpaEntity, UUID> {
    Optional<UserAccountJpaEntity> findByEmail(String email);
}
