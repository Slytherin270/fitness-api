package com.fitnessapp.progress.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class UserProgress {

    private final UUID userId;
    private long totalWorkouts;
    private BigDecimal totalVolume;
    private long personalRecords;
    private Instant lastCompletedAt;

    public UserProgress(UUID userId) {
        this(userId, 0, BigDecimal.ZERO, 0, null);
    }

    public UserProgress(UUID userId,
                        long totalWorkouts,
                        BigDecimal totalVolume,
                        long personalRecords,
                        Instant lastCompletedAt) {
        this.userId = userId;
        this.totalWorkouts = totalWorkouts;
        this.totalVolume = totalVolume;
        this.personalRecords = personalRecords;
        this.lastCompletedAt = lastCompletedAt;
    }

    public void registerWorkout(BigDecimal workoutVolume, Instant completedAt, boolean personalRecordAchieved) {
        this.totalWorkouts += 1;
        this.totalVolume = this.totalVolume.add(workoutVolume);
        if (personalRecordAchieved) {
            this.personalRecords += 1;
        }
        this.lastCompletedAt = completedAt;
    }

    public UUID userId() {
        return userId;
    }

    public long totalWorkouts() {
        return totalWorkouts;
    }

    public BigDecimal totalVolume() {
        return totalVolume;
    }

    public long personalRecords() {
        return personalRecords;
    }

    public Instant lastCompletedAt() {
        return lastCompletedAt;
    }
}
