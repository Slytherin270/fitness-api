package com.fitnessapp.workout;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fitnessapp.workout.application.WorkoutCompletedEvent;
import com.fitnessapp.workout.application.WorkoutRecordGateway;
import com.fitnessapp.workout.application.WorkoutRepository;
import com.fitnessapp.workout.application.WorkoutUseCase;
import com.fitnessapp.workout.application.commands.CompleteWorkoutCommand;
import com.fitnessapp.workout.domain.Workout;
import com.fitnessapp.workout.domain.WorkoutId;
import com.fitnessapp.workout.domain.WorkoutSet;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

class WorkoutUseCaseTest {

    @Test
    void shouldPublishCompletionEvent() {
        WorkoutRepository repository = mock(WorkoutRepository.class);
        WorkoutRecordGateway gateway = mock(WorkoutRecordGateway.class);
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);

        Workout workout = new Workout(WorkoutId.newId(), UUID.randomUUID(), "Pull");
        UUID exerciseId = UUID.randomUUID();
        workout.addExercise(exerciseId);
        workout.exercise(exerciseId).addSet(new WorkoutSet(5, new BigDecimal("80")));
        workout.start();

        when(repository.findById(any())).thenReturn(Optional.of(workout));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(gateway.bestLift(any(), any())).thenReturn(Optional.of(new BigDecimal("70")));

        WorkoutUseCase useCase = new WorkoutUseCase(repository, gateway, publisher);
        useCase.complete(new CompleteWorkoutCommand(workout.id().value()));

        verify(publisher).publishEvent(any(WorkoutCompletedEvent.class));
        assertTrue(workout.totalVolume().compareTo(BigDecimal.ZERO) > 0);
    }
}
