package com.fitnessapp.workout.infrastructure;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataWorkoutRepository extends JpaRepository<WorkoutJpaEntity, UUID> {
    Page<WorkoutJpaEntity> findByUserId(UUID userId, Pageable pageable);
}
