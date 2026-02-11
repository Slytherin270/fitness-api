package com.fitnessapp.analytics.application;

import com.fitnessapp.nutrition.application.NutritionUseCase;
import com.fitnessapp.plan.application.PlanUseCase;
import com.fitnessapp.progress.application.ProgressQueryService;
import com.fitnessapp.progress.domain.UserProgress;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnalyticsQueryService {

    private final ProgressQueryService progressQueryService;
    private final NutritionUseCase nutritionUseCase;
    private final PlanUseCase planUseCase;

    public AnalyticsQueryService(ProgressQueryService progressQueryService,
                                 NutritionUseCase nutritionUseCase,
                                 PlanUseCase planUseCase) {
        this.progressQueryService = progressQueryService;
        this.nutritionUseCase = nutritionUseCase;
        this.planUseCase = planUseCase;
    }

    @Transactional(readOnly = true)
    public AnalyticsSummary summary(UUID userId) {
        UserProgress progress = progressQueryService.byUserId(userId);
        LocalDate today = LocalDate.now();
        int calories = nutritionUseCase.caloriesForRange(userId, today.minusDays(6), today);

        return new AnalyticsSummary(
                progress.totalWorkouts(),
                progress.totalVolume(),
                progress.personalRecords(),
                calories,
                planUseCase.activePlanCount(userId));
    }
}
