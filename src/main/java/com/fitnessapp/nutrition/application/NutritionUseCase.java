package com.fitnessapp.nutrition.application;

import com.fitnessapp.nutrition.domain.NutritionLog;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NutritionUseCase {

    private final NutritionRepository repository;

    public NutritionUseCase(NutritionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public NutritionLog logMeal(UUID userId, LocalDate loggedOn, String mealType, int calories,
                                BigDecimal proteinGrams, BigDecimal carbsGrams, BigDecimal fatGrams) {
        NutritionLog nutritionLog = new NutritionLog(
                UUID.randomUUID(),
                userId,
                loggedOn,
                mealType,
                calories,
                proteinGrams,
                carbsGrams,
                fatGrams);
        return repository.save(nutritionLog);
    }

    @Transactional(readOnly = true)
    public List<NutritionLog> byDate(UUID userId, LocalDate loggedOn) {
        return repository.findByUserIdAndDate(userId, loggedOn);
    }

    @Transactional(readOnly = true)
    public int caloriesForRange(UUID userId, LocalDate fromInclusive, LocalDate toInclusive) {
        return repository.totalCalories(userId, fromInclusive, toInclusive);
    }
}
