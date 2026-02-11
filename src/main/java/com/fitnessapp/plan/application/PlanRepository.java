package com.fitnessapp.plan.application;

import com.fitnessapp.plan.domain.TrainingPlan;
import java.util.List;
import java.util.UUID;

public interface PlanRepository {
    TrainingPlan save(TrainingPlan trainingPlan);

    List<TrainingPlan> findByUserId(UUID userId);

    long countActivePlans(UUID userId);
}
