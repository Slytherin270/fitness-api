package com.fitnessapp.plan.infrastructure;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPlanRepository extends JpaRepository<TrainingPlanJpaEntity, UUID> {
    List<TrainingPlanJpaEntity> findByUserId(UUID userId);

    long countByUserIdAndActiveTrue(UUID userId);
}
