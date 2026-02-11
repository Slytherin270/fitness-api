package com.fitnessapp.nutrition.infrastructure;

import com.fitnessapp.nutrition.application.NutritionRepository;
import com.fitnessapp.nutrition.domain.NutritionLog;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class NutritionRepositoryAdapter implements NutritionRepository {

    private final SpringDataNutritionRepository repository;

    public NutritionRepositoryAdapter(SpringDataNutritionRepository repository) {
        this.repository = repository;
    }

    @Override
    public NutritionLog save(NutritionLog nutritionLog) {
        NutritionLogJpaEntity entity = new NutritionLogJpaEntity();
        entity.setId(nutritionLog.id());
        entity.setUserId(nutritionLog.userId());
        entity.setLoggedOn(nutritionLog.loggedOn());
        entity.setMealType(nutritionLog.mealType());
        entity.setCalories(nutritionLog.calories());
        entity.setProteinGrams(nutritionLog.proteinGrams());
        entity.setCarbsGrams(nutritionLog.carbsGrams());
        entity.setFatGrams(nutritionLog.fatGrams());
        return toDomain(repository.save(entity));
    }

    @Override
    public List<NutritionLog> findByUserIdAndDate(UUID userId, LocalDate loggedOn) {
        return repository.findByUserIdAndLoggedOn(userId, loggedOn).stream().map(this::toDomain).toList();
    }

    @Override
    public int totalCalories(UUID userId, LocalDate fromInclusive, LocalDate toInclusive) {
        return repository.totalCalories(userId, fromInclusive, toInclusive);
    }

    private NutritionLog toDomain(NutritionLogJpaEntity entity) {
        return new NutritionLog(entity.getId(), entity.getUserId(), entity.getLoggedOn(), entity.getMealType(), entity.getCalories(),
                entity.getProteinGrams(), entity.getCarbsGrams(), entity.getFatGrams());
    }
}
