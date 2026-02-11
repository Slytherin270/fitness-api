package com.fitnessapp.progress.interfaceadapters;

import com.fitnessapp.progress.application.ProgressQueryService;
import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressQueryService queryService;
    private final ProgressResponseMapper mapper;

    public ProgressController(ProgressQueryService queryService, ProgressResponseMapper mapper) {
        this.queryService = queryService;
        this.mapper = mapper;
    }

    @GetMapping("/me")
    public ProgressDtos.ProgressResponse myProgress(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return mapper.toResponse(queryService.byUserId(userId));
    }
}
