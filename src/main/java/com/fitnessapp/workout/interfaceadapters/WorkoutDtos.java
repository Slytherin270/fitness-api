package com.fitnessapp.workout.interfaceadapters;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class WorkoutDtos {
    private WorkoutDtos() {}

    public record CreateWorkoutRequest(@NotBlank String name) {}
    public record AddExerciseRequest(@NotNull UUID exerciseId) {}
    public record AddSetRequest(@NotNull UUID exerciseId, @Min(1) int reps, @NotNull BigDecimal weight) {}
    public record WorkoutSetResponse(int reps, BigDecimal weight) {}
    public record WorkoutExerciseResponse(UUID exerciseId, List<WorkoutSetResponse> sets) {}
    public record WorkoutResponse(UUID id, UUID userId, String name, String status, Instant startedAt, Instant completedAt,
                                  List<WorkoutExerciseResponse> exercises, BigDecimal totalVolume) {}
}
