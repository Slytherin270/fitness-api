package com.fitnessapp.exercise.application;

import com.fitnessapp.exercise.domain.Exercise;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseQueryService {
    private final ExerciseRepository repository;

    public ExerciseQueryService(ExerciseRepository repository) {
        this.repository = repository;
    }

    @Cacheable(cacheNames = "publicExercises")
    public List<Exercise> publicExercises() {
        return repository.findPublicExercises();
    }

    @CacheEvict(cacheNames = "publicExercises", allEntries = true)
    public void invalidatePublicCache() {
        // called after exercise admin mutations
    }
}
