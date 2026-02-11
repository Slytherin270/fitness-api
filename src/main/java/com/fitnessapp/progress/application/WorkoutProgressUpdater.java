package com.fitnessapp.progress.application;

import com.fitnessapp.progress.domain.UserProgress;
import com.fitnessapp.workout.application.WorkoutCompletedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WorkoutProgressUpdater {

    private final ProgressRepository repository;

    public WorkoutProgressUpdater(ProgressRepository repository) {
        this.repository = repository;
    }

    @ApplicationModuleListener
    @Transactional
    public void onWorkoutCompleted(WorkoutCompletedEvent event) {
        UserProgress progress = repository.findByUserId(event.userId())
                .orElse(new UserProgress(event.userId()));

        progress.registerWorkout(event.totalVolume(), event.completedAt(), event.personalRecordAchieved());
        repository.save(progress);
    }
}
