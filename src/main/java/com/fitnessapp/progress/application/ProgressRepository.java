package com.fitnessapp.progress.application;

import com.fitnessapp.progress.domain.UserProgress;
import java.util.Optional;
import java.util.UUID;

public interface ProgressRepository {
    Optional<UserProgress> findByUserId(UUID userId);

    UserProgress save(UserProgress progress);
}
