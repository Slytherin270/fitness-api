package com.fitnessapp.progress.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProgressRepository extends JpaRepository<UserProgressJpaEntity, UUID> {
}
