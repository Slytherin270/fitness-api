package com.fitnessapp.progress.application;

import com.fitnessapp.progress.domain.UserProgress;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgressQueryService {

    private final ProgressRepository repository;

    public ProgressQueryService(ProgressRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public UserProgress byUserId(UUID userId) {
        return repository.findByUserId(userId).orElse(new UserProgress(userId));
    }
}
