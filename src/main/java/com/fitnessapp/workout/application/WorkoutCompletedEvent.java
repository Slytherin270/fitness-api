package com.fitnessapp.workout.application;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WorkoutCompletedEvent(UUID workoutId, UUID userId, BigDecimal totalVolume, Instant completedAt, boolean personalRecordAchieved) {
}
