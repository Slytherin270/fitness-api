package com.fitnessapp.analytics.interfaceadapters;

import com.fitnessapp.analytics.application.AnalyticsQueryService;
import com.fitnessapp.analytics.application.AnalyticsSummary;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsQueryService queryService;

    public AnalyticsController(AnalyticsQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/me")
    public AnalyticsResponse mySummary(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        AnalyticsSummary summary = queryService.summary(userId);
        return new AnalyticsResponse(summary.completedWorkouts(), summary.totalVolume(), summary.personalRecords(),
                summary.caloriesLast7Days(), summary.activePlans());
    }

    public record AnalyticsResponse(long completedWorkouts, BigDecimal totalVolume, long personalRecords,
                                    int caloriesLast7Days, long activePlans) {
    }
}
