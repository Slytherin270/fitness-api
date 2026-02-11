package com.fitnessapp.plan.interfaceadapters;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public final class PlanDtos {
    private PlanDtos() {
    }

    public record CreatePlanRequest(@NotBlank String name, @NotBlank String goal) {
    }

    public record PlanResponse(UUID id, String name, String goal, boolean active) {
    }
}
