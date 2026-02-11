package com.fitnessapp.analytics.application;

import java.math.BigDecimal;

public record AnalyticsSummary(long completedWorkouts, BigDecimal totalVolume, long personalRecords,
                               int caloriesLast7Days, long activePlans) {
}
