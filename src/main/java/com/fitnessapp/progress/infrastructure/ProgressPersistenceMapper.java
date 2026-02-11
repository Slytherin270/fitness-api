package com.fitnessapp.progress.infrastructure;

import com.fitnessapp.progress.domain.UserProgress;
import org.springframework.stereotype.Component;

@Component
public class ProgressPersistenceMapper {

    UserProgressJpaEntity toJpa(UserProgress domain) {
        UserProgressJpaEntity entity = new UserProgressJpaEntity();
        entity.setUserId(domain.userId());
        entity.setTotalWorkouts(domain.totalWorkouts());
        entity.setTotalVolume(domain.totalVolume());
        entity.setPersonalRecords(domain.personalRecords());
        entity.setLastCompletedAt(domain.lastCompletedAt());
        return entity;
    }

    UserProgress toDomain(UserProgressJpaEntity entity) {
        return new UserProgress(
                entity.getUserId(),
                entity.getTotalWorkouts(),
                entity.getTotalVolume(),
                entity.getPersonalRecords(),
                entity.getLastCompletedAt()
        );
    }
}
