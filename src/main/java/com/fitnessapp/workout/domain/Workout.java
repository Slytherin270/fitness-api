package com.fitnessapp.workout.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Workout {
    private final WorkoutId id;
    private final UUID userId;
    private final String name;
    private final List<WorkoutExercise> exercises;
    private WorkoutStatus status;
    private Instant startedAt;
    private Instant completedAt;

    public Workout(WorkoutId id, UUID userId, String name) {
        if (userId == null || name == null || name.isBlank()) {
            throw new IllegalArgumentException("User and name are required");
        }
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.status = WorkoutStatus.DRAFT;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(UUID exerciseId) {
        exercises.add(new WorkoutExercise(exerciseId));
    }

    public void start() {
        if (status != WorkoutStatus.DRAFT) {
            throw new IllegalArgumentException("Workout can only be started from draft state");
        }
        status = WorkoutStatus.IN_PROGRESS;
        startedAt = Instant.now();
    }

    public void complete() {
        if (status != WorkoutStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("Workout must be in progress to complete");
        }
        status = WorkoutStatus.COMPLETED;
        completedAt = Instant.now();
    }

    public BigDecimal totalVolume() {
        return exercises.stream().map(WorkoutExercise::totalVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public WorkoutExercise exercise(UUID exerciseId) {
        return exercises.stream().filter(e -> e.exerciseId().equals(exerciseId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found in workout"));
    }

    public WorkoutId id() { return id; }
    public UUID userId() { return userId; }
    public String name() { return name; }
    public WorkoutStatus status() { return status; }
    public Instant startedAt() { return startedAt; }
    public Instant completedAt() { return completedAt; }
    public List<WorkoutExercise> exercises() { return List.copyOf(exercises); }
}
