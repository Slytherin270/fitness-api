package com.fitnessapp.notification.application;

import com.fitnessapp.workout.application.WorkoutCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class WorkoutAchievementListener {

    private static final Logger log = LoggerFactory.getLogger(WorkoutAchievementListener.class);

    @ApplicationModuleListener
    public void onWorkoutCompleted(WorkoutCompletedEvent event) {
        if (event.personalRecordAchieved()) {
            log.info("Trigger achievement notification for user {} on workout {}", event.userId(), event.workoutId());
        }
    }
}
