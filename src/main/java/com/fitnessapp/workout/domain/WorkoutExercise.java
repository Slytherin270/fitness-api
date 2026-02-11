package com.fitnessapp.workout.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkoutExercise {
    private final UUID exerciseId;
    private final List<WorkoutSet> sets = new ArrayList<>();

    public WorkoutExercise(UUID exerciseId) {
        if (exerciseId == null) {
            throw new IllegalArgumentException("Exercise id cannot be null");
        }
        this.exerciseId = exerciseId;
    }

    public UUID exerciseId() {
        return exerciseId;
    }

    public void addSet(WorkoutSet set) {
        sets.add(set);
    }

    public List<WorkoutSet> sets() {
        return List.copyOf(sets);
    }

    public BigDecimal totalVolume() {
        return sets.stream().map(WorkoutSet::volume).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
