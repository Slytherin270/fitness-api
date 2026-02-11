package com.fitnessapp.nutrition.interfaceadapters;

import com.fitnessapp.nutrition.application.NutritionUseCase;
import com.fitnessapp.nutrition.domain.NutritionLog;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nutrition/logs")
public class NutritionController {

    private final NutritionUseCase nutritionUseCase;

    public NutritionController(NutritionUseCase nutritionUseCase) {
        this.nutritionUseCase = nutritionUseCase;
    }

    @PostMapping
    public NutritionDtos.NutritionLogResponse logMeal(@AuthenticationPrincipal Jwt jwt,
                                                      @Valid @RequestBody NutritionDtos.LogMealRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        NutritionLog log = nutritionUseCase.logMeal(userId, request.loggedOn(), request.mealType(), request.calories(),
                request.proteinGrams(), request.carbsGrams(), request.fatGrams());
        return toResponse(log);
    }

    @GetMapping("/me")
    public List<NutritionDtos.NutritionLogResponse> byDate(@AuthenticationPrincipal Jwt jwt,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return nutritionUseCase.byDate(userId, date).stream().map(this::toResponse).toList();
    }

    private NutritionDtos.NutritionLogResponse toResponse(NutritionLog log) {
        return new NutritionDtos.NutritionLogResponse(
                log.id(),
                log.loggedOn(),
                log.mealType(),
                log.calories(),
                log.proteinGrams(),
                log.carbsGrams(),
                log.fatGrams());
    }
}
