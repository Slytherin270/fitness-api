package com.fitnessapp.workout.interfaceadapters;

import com.fitnessapp.workout.domain.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutResponseMapper {

    public WorkoutDtos.WorkoutResponse toResponse(Workout workout) {
        return new WorkoutDtos.WorkoutResponse(
                workout.id().value(),
                workout.userId(),
                workout.name(),
                workout.status().name(),
                workout.startedAt(),
                workout.completedAt(),
                workout.exercises().stream().map(ex -> new WorkoutDtos.WorkoutExerciseResponse(
                        ex.exerciseId(),
                        ex.sets().stream().map(set -> new WorkoutDtos.WorkoutSetResponse(set.reps(), set.weight())).toList()
                )).toList(),
                workout.totalVolume());
    }
}
