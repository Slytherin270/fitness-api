package com.fitnessapp.workout.infrastructure;

import com.fitnessapp.workout.application.WorkoutRepository;
import com.fitnessapp.workout.domain.Workout;
import com.fitnessapp.workout.domain.WorkoutId;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class WorkoutRepositoryAdapter implements WorkoutRepository {
    private final SpringDataWorkoutRepository repository;
    private final WorkoutPersistenceMapper mapper;

    public WorkoutRepositoryAdapter(SpringDataWorkoutRepository repository, WorkoutPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Workout save(Workout workout) {
        return mapper.toDomain(repository.save(mapper.toJpa(workout)));
    }

    @Override
    public Optional<Workout> findById(WorkoutId workoutId) {
        return repository.findById(workoutId.value()).map(mapper::toDomain);
    }

    @Override
    public Page<Workout> findByUser(UUID userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable).map(mapper::toDomain);
    }
}
