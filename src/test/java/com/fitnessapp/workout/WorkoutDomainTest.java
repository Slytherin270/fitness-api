package com.fitnessapp.workout;

import static org.junit.jupiter.api.Assertions.*;

import com.fitnessapp.workout.domain.Workout;
import com.fitnessapp.workout.domain.WorkoutId;
import com.fitnessapp.workout.domain.WorkoutSet;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class WorkoutDomainTest {

    @Test
    void shouldCalculateTotalVolume() {
        Workout workout = new Workout(WorkoutId.newId(), UUID.randomUUID(), "Leg day");
        workout.addExercise(UUID.randomUUID());
        var exercise = workout.exercises().getFirst();
        exercise.addSet(new WorkoutSet(5, new BigDecimal("100")));
        exercise.addSet(new WorkoutSet(5, new BigDecimal("110")));

        assertEquals(new BigDecimal("1050"), workout.totalVolume());
    }

    @Test
    void shouldCompleteOnlyWhenInProgress() {
        Workout workout = new Workout(WorkoutId.newId(), UUID.randomUUID(), "Push");
        assertThrows(IllegalArgumentException.class, workout::complete);
        workout.start();
        assertDoesNotThrow(workout::complete);
    }
}
