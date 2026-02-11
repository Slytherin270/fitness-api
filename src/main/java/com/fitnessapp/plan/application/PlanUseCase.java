package com.fitnessapp.plan.application;

import com.fitnessapp.plan.domain.TrainingPlan;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanUseCase {

    private final PlanRepository repository;

    public PlanUseCase(PlanRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TrainingPlan create(UUID userId, String name, String goal) {
        Instant now = Instant.now();
        return repository.save(new TrainingPlan(UUID.randomUUID(), userId, name, goal, true, now, now));
    }

    @Transactional(readOnly = true)
    public List<TrainingPlan> myPlans(UUID userId) {
        return repository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public long activePlanCount(UUID userId) {
        return repository.countActivePlans(userId);
    }
}
