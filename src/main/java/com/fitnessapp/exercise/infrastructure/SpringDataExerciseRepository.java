package com.fitnessapp.exercise.infrastructure;

import com.fitnessapp.exercise.domain.ExerciseVisibility;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataExerciseRepository extends JpaRepository<ExerciseJpaEntity, UUID> {
    List<ExerciseJpaEntity> findByVisibility(ExerciseVisibility visibility);
}
