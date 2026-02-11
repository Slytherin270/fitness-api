package com.fitnessapp.progress.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_progress")
public class UserProgressJpaEntity {

    @Id
    private UUID userId;

    @Column(nullable = false)
    private long totalWorkouts;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal totalVolume;

    @Column(nullable = false)
    private long personalRecords;

    private Instant lastCompletedAt;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public long getTotalWorkouts() {
        return totalWorkouts;
    }

    public void setTotalWorkouts(long totalWorkouts) {
        this.totalWorkouts = totalWorkouts;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public long getPersonalRecords() {
        return personalRecords;
    }

    public void setPersonalRecords(long personalRecords) {
        this.personalRecords = personalRecords;
    }

    public Instant getLastCompletedAt() {
        return lastCompletedAt;
    }

    public void setLastCompletedAt(Instant lastCompletedAt) {
        this.lastCompletedAt = lastCompletedAt;
    }
}
