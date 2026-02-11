package com.fitnessapp.plan.domain;

import java.time.Instant;
import java.util.UUID;

public record TrainingPlan(UUID id, UUID userId, String name, String goal, boolean active, Instant createdAt, Instant updatedAt) {
}
