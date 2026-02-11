package com.fitnessapp.workout.application;

import com.fitnessapp.workout.domain.Workout;
import com.fitnessapp.workout.domain.WorkoutId;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkoutRepository {
    Workout save(Workout workout);
    Optional<Workout> findById(WorkoutId workoutId);
    Page<Workout> findByUser(UUID userId, Pageable pageable);
}
