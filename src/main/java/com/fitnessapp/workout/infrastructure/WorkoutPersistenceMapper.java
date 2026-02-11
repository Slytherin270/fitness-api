package com.fitnessapp.workout.infrastructure;

import com.fitnessapp.workout.domain.Workout;
import com.fitnessapp.workout.domain.WorkoutExercise;
import com.fitnessapp.workout.domain.WorkoutId;
import com.fitnessapp.workout.domain.WorkoutSet;
import com.fitnessapp.workout.domain.WorkoutStatus;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class WorkoutPersistenceMapper {

    public WorkoutJpaEntity toJpa(Workout workout) {
        WorkoutJpaEntity entity = new WorkoutJpaEntity();
        entity.setId(workout.id().value());
        entity.setUserId(workout.userId());
        entity.setName(workout.name());
        entity.setStatus(WorkoutStatusJpa.valueOf(workout.status().name()));
        entity.setStartedAt(workout.startedAt());
        entity.setCompletedAt(workout.completedAt());

        entity.getExercises().clear();
        entity.getExercises().addAll(workout.exercises().stream().map(exercise -> {
            WorkoutExerciseJpaEntity exerciseEntity = new WorkoutExerciseJpaEntity();
            exerciseEntity.setWorkout(entity);
            exerciseEntity.setExerciseId(exercise.exerciseId());
            exerciseEntity.getSets().addAll(exercise.sets().stream().map(set -> {
                WorkoutSetJpaEntity setEntity = new WorkoutSetJpaEntity();
                setEntity.setWorkoutExercise(exerciseEntity);
                setEntity.setReps(set.reps());
                setEntity.setWeight(set.weight());
                return setEntity;
            }).toList());
            return exerciseEntity;
        }).collect(Collectors.toList()));

        return entity;
    }

    public Workout toDomain(WorkoutJpaEntity entity) {
        Workout workout = new Workout(new WorkoutId(entity.getId()), entity.getUserId(), entity.getName());
        if (entity.getStatus() == WorkoutStatusJpa.IN_PROGRESS) {
            workout.start();
        }
        if (entity.getStatus() == WorkoutStatusJpa.COMPLETED) {
            workout.start();
            workout.complete();
        }
        entity.getExercises().forEach(ex -> {
            workout.addExercise(ex.getExerciseId());
            WorkoutExercise workoutExercise = workout.exercise(ex.getExerciseId());
            ex.getSets().forEach(set -> workoutExercise.addSet(new WorkoutSet(set.getReps(), set.getWeight())));
        });
        return workout;
    }
}
