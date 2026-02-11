package com.fitnessapp.progress;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fitnessapp.progress.domain.UserProgress;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserProgressTest {

    @Test
    void shouldAggregateCompletedWorkoutStats() {
        UserProgress progress = new UserProgress(UUID.randomUUID());
        Instant completedAt = Instant.parse("2025-01-02T10:15:30Z");

        progress.registerWorkout(new BigDecimal("4200"), completedAt, true);

        assertEquals(1, progress.totalWorkouts());
        assertEquals(new BigDecimal("4200"), progress.totalVolume());
        assertEquals(1, progress.personalRecords());
        assertEquals(completedAt, progress.lastCompletedAt());
    }
}
