package com.fitnessapp.progress.interfaceadapters;

import com.fitnessapp.progress.domain.UserProgress;
import org.springframework.stereotype.Component;

@Component
public class ProgressResponseMapper {

    public ProgressDtos.ProgressResponse toResponse(UserProgress progress) {
        return new ProgressDtos.ProgressResponse(
                progress.userId(),
                progress.totalWorkouts(),
                progress.totalVolume(),
                progress.personalRecords(),
                progress.lastCompletedAt()
        );
    }
}
