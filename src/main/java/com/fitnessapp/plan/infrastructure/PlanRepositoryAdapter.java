package com.fitnessapp.plan.infrastructure;

import com.fitnessapp.plan.application.PlanRepository;
import com.fitnessapp.plan.domain.TrainingPlan;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PlanRepositoryAdapter implements PlanRepository {

    private final SpringDataPlanRepository repository;

    public PlanRepositoryAdapter(SpringDataPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public TrainingPlan save(TrainingPlan trainingPlan) {
        TrainingPlanJpaEntity entity = new TrainingPlanJpaEntity();
        entity.setId(trainingPlan.id());
        entity.setUserId(trainingPlan.userId());
        entity.setName(trainingPlan.name());
        entity.setGoal(trainingPlan.goal());
        entity.setActive(trainingPlan.active());
        entity.setCreatedAt(trainingPlan.createdAt());
        entity.setUpdatedAt(trainingPlan.updatedAt());
        return toDomain(repository.save(entity));
    }

    @Override
    public List<TrainingPlan> findByUserId(UUID userId) {
        return repository.findByUserId(userId).stream().map(this::toDomain).toList();
    }

    @Override
    public long countActivePlans(UUID userId) {
        return repository.countByUserIdAndActiveTrue(userId);
    }

    private TrainingPlan toDomain(TrainingPlanJpaEntity entity) {
        return new TrainingPlan(entity.getId(), entity.getUserId(), entity.getName(), entity.getGoal(), entity.isActive(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
