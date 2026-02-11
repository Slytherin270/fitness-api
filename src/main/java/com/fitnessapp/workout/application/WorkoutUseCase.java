package com.fitnessapp.workout.application;

import com.fitnessapp.workout.application.commands.AddExerciseCommand;
import com.fitnessapp.workout.application.commands.AddSetCommand;
import com.fitnessapp.workout.application.commands.CompleteWorkoutCommand;
import com.fitnessapp.workout.application.commands.CreateWorkoutCommand;
import com.fitnessapp.workout.domain.PersonalRecordPolicy;
import com.fitnessapp.workout.domain.Workout;
import com.fitnessapp.workout.domain.WorkoutId;
import com.fitnessapp.workout.domain.WorkoutSet;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkoutUseCase {

    private final WorkoutRepository workoutRepository;
    private final WorkoutRecordGateway workoutRecordGateway;
    private final ApplicationEventPublisher eventPublisher;
    private final PersonalRecordPolicy personalRecordPolicy = new PersonalRecordPolicy();

    public WorkoutUseCase(WorkoutRepository workoutRepository,
                          WorkoutRecordGateway workoutRecordGateway,
                          ApplicationEventPublisher eventPublisher) {
        this.workoutRepository = workoutRepository;
        this.workoutRecordGateway = workoutRecordGateway;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Workout create(@Valid CreateWorkoutCommand command) {
        Workout workout = new Workout(WorkoutId.newId(), command.userId(), command.name());
        workout.start();
        return workoutRepository.save(workout);
    }

    @Transactional
    public Workout addExercise(@Valid AddExerciseCommand command) {
        Workout workout = load(command.workoutId());
        workout.addExercise(command.exerciseId());
        return workoutRepository.save(workout);
    }

    @Transactional
    public Workout addSet(@Valid AddSetCommand command) {
        Workout workout = load(command.workoutId());
        workout.exercise(command.exerciseId()).addSet(new WorkoutSet(command.reps(), command.weight()));
        return workoutRepository.save(workout);
    }

    @Transactional
    public Workout complete(@Valid CompleteWorkoutCommand command) {
        Workout workout = load(command.workoutId());
        workout.complete();
        var persisted = workoutRepository.save(workout);

        BigDecimal best = workout.exercises().stream()
                .map(e -> e.sets().stream().map(WorkoutSet::weight).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO))
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        boolean pr = persisted.exercises().stream().anyMatch(exercise ->
                personalRecordPolicy.isNewRecord(
                        workoutRecordGateway.bestLift(persisted.userId(), exercise.exerciseId()).orElse(null),
                        exercise.sets().stream().map(WorkoutSet::weight).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO)
                ));

        eventPublisher.publishEvent(new WorkoutCompletedEvent(
                persisted.id().value(),
                persisted.userId(),
                persisted.totalVolume(),
                Instant.now(),
                pr && best.signum() > 0
        ));

        return persisted;
    }

    @Transactional(readOnly = true)
    public Page<Workout> history(java.util.UUID userId, Pageable pageable) {
        return workoutRepository.findByUser(userId, pageable);
    }

    private Workout load(java.util.UUID workoutId) {
        return workoutRepository.findById(new WorkoutId(workoutId))
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));
    }
}
