package com.fitnessapp.exercise.application;

import com.fitnessapp.exercise.domain.Exercise;
import java.util.List;

public interface ExerciseRepository {
    List<Exercise> findPublicExercises();
}
