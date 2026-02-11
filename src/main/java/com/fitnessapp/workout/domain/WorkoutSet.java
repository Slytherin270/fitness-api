package com.fitnessapp.workout.domain;

import java.math.BigDecimal;

public record WorkoutSet(int reps, BigDecimal weight) {
    public WorkoutSet {
        if (reps <= 0) {
            throw new IllegalArgumentException("Reps must be positive");
        }
        if (weight == null || weight.signum() < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
    }

    public BigDecimal volume() {
        return weight.multiply(BigDecimal.valueOf(reps));
    }
}
