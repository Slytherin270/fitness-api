package com.fitnessapp.nutrition.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record NutritionLog(
        UUID id,
        UUID userId,
        LocalDate loggedOn,
        String mealType,
        int calories,
        BigDecimal proteinGrams,
        BigDecimal carbsGrams,
        BigDecimal fatGrams) {
}
