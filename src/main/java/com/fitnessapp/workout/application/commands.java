package com.fitnessapp.workout.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public final class commands {
    private commands() {}

    public record CreateWorkoutCommand(@NotNull UUID userId, @NotBlank String name) {}

    public record AddExerciseCommand(@NotNull UUID workoutId, @NotNull UUID exerciseId) {}

    public record AddSetCommand(@NotNull UUID workoutId, @NotNull UUID exerciseId, int reps, @NotNull BigDecimal weight) {}

    public record CompleteWorkoutCommand(@NotNull UUID workoutId) {}
}
