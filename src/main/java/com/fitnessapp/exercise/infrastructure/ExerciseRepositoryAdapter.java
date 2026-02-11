package com.fitnessapp.exercise.infrastructure;

import com.fitnessapp.exercise.application.ExerciseRepository;
import com.fitnessapp.exercise.domain.Exercise;
import com.fitnessapp.exercise.domain.ExerciseVisibility;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseRepositoryAdapter implements ExerciseRepository {

    private final SpringDataExerciseRepository repository;

    public ExerciseRepositoryAdapter(SpringDataExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Exercise> findPublicExercises() {
        return repository.findByVisibility(ExerciseVisibility.PUBLIC)
                .stream()
                .map(e -> new Exercise(e.getId(), e.getName(), e.getCategory(), e.getMuscleGroup(), e.getEquipmentType(), e.getVisibility()))
                .toList();
    }
}
