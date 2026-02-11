package com.fitnessapp.nutrition.interfaceadapters;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public final class NutritionDtos {
    private NutritionDtos() {
    }

    public record LogMealRequest(
            @NotNull LocalDate loggedOn,
            @NotBlank String mealType,
            @Min(0) int calories,
            @NotNull BigDecimal proteinGrams,
            @NotNull BigDecimal carbsGrams,
            @NotNull BigDecimal fatGrams) {
    }

    public record NutritionLogResponse(UUID id, LocalDate loggedOn, String mealType, int calories,
                                       BigDecimal proteinGrams, BigDecimal carbsGrams, BigDecimal fatGrams) {
    }
}
