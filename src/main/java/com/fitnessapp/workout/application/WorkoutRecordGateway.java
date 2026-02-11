package com.fitnessapp.workout.application;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutRecordGateway {
    Optional<BigDecimal> bestLift(UUID userId, UUID exerciseId);
}
