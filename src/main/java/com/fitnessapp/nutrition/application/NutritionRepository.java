package com.fitnessapp.nutrition.application;

import com.fitnessapp.nutrition.domain.NutritionLog;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NutritionRepository {
    NutritionLog save(NutritionLog nutritionLog);

    List<NutritionLog> findByUserIdAndDate(UUID userId, LocalDate loggedOn);

    int totalCalories(UUID userId, LocalDate fromInclusive, LocalDate toInclusive);
}
