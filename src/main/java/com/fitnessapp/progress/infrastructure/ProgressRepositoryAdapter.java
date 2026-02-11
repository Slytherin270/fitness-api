package com.fitnessapp.progress.infrastructure;

import com.fitnessapp.progress.application.ProgressRepository;
import com.fitnessapp.progress.domain.UserProgress;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProgressRepositoryAdapter implements ProgressRepository {

    private final SpringDataProgressRepository repository;
    private final ProgressPersistenceMapper mapper;

    public ProgressRepositoryAdapter(SpringDataProgressRepository repository, ProgressPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<UserProgress> findByUserId(UUID userId) {
        return repository.findById(userId).map(mapper::toDomain);
    }

    @Override
    public UserProgress save(UserProgress progress) {
        UserProgressJpaEntity persisted = repository.save(mapper.toJpa(progress));
        return mapper.toDomain(persisted);
    }
}
