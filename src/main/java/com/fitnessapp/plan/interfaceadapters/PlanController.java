package com.fitnessapp.plan.interfaceadapters;

import com.fitnessapp.plan.application.PlanUseCase;
import com.fitnessapp.plan.domain.TrainingPlan;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanUseCase planUseCase;

    public PlanController(PlanUseCase planUseCase) {
        this.planUseCase = planUseCase;
    }

    @PostMapping
    public PlanDtos.PlanResponse create(@AuthenticationPrincipal Jwt jwt,
                                        @Valid @RequestBody PlanDtos.CreatePlanRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return toResponse(planUseCase.create(userId, request.name(), request.goal()));
    }

    @GetMapping("/me")
    public List<PlanDtos.PlanResponse> myPlans(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return planUseCase.myPlans(userId).stream().map(this::toResponse).toList();
    }

    private PlanDtos.PlanResponse toResponse(TrainingPlan plan) {
        return new PlanDtos.PlanResponse(plan.id(), plan.name(), plan.goal(), plan.active());
    }
}
