package com.fitnessapp.progress.interfaceadapters;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public final class ProgressDtos {

    private ProgressDtos() {
    }

    public record ProgressResponse(
            UUID userId,
            long totalWorkouts,
            BigDecimal totalVolume,
            long personalRecords,
            Instant lastCompletedAt
    ) {
    }
}
