package com.fitnessapp.workout.domain;

import java.util.UUID;

public record WorkoutId(UUID value) {
    public WorkoutId {
        if (value == null) {
            throw new IllegalArgumentException("Workout id cannot be null");
        }
    }

    public static WorkoutId newId() {
        return new WorkoutId(UUID.randomUUID());
    }
}
