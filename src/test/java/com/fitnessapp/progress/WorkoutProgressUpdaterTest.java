package com.fitnessapp.progress;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fitnessapp.progress.application.ProgressRepository;
import com.fitnessapp.progress.application.WorkoutProgressUpdater;
import com.fitnessapp.progress.domain.UserProgress;
import com.fitnessapp.workout.application.WorkoutCompletedEvent;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class WorkoutProgressUpdaterTest {

    @Test
    void shouldCreateProgressWhenFirstWorkoutArrives() {
        ProgressRepository repository = Mockito.mock(ProgressRepository.class);
        WorkoutProgressUpdater updater = new WorkoutProgressUpdater(repository);

        UUID userId = UUID.randomUUID();
        WorkoutCompletedEvent event = new WorkoutCompletedEvent(
                UUID.randomUUID(),
                userId,
                new BigDecimal("950"),
                Instant.parse("2025-01-02T10:15:30Z"),
                false
        );

        when(repository.findByUserId(userId)).thenReturn(Optional.empty());

        updater.onWorkoutCompleted(event);

        ArgumentCaptor<UserProgress> captor = ArgumentCaptor.forClass(UserProgress.class);
        verify(repository).save(captor.capture());
        UserProgress saved = captor.getValue();
        assertEquals(1, saved.totalWorkouts());
        assertEquals(0, saved.totalVolume().compareTo(new BigDecimal("950")));
    }

    @Test
    void shouldAccumulatePersonalRecordCount() {
        ProgressRepository repository = Mockito.mock(ProgressRepository.class);
        WorkoutProgressUpdater updater = new WorkoutProgressUpdater(repository);

        UUID userId = UUID.randomUUID();
        UserProgress existing = new UserProgress(userId, 3, new BigDecimal("3000"), 1, Instant.now());
        when(repository.findByUserId(userId)).thenReturn(Optional.of(existing));

        updater.onWorkoutCompleted(new WorkoutCompletedEvent(
                UUID.randomUUID(),
                userId,
                new BigDecimal("1200"),
                Instant.parse("2025-02-01T06:00:00Z"),
                true
        ));

        verify(repository).save(any(UserProgress.class));
        assertEquals(4, existing.totalWorkouts());
        assertEquals(2, existing.personalRecords());
        assertEquals(0, existing.totalVolume().compareTo(new BigDecimal("4200")));
    }
}
