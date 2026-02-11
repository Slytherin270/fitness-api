package com.fitnessapp.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fitnessapp.workout.domain.Workout;
import com.fitnessapp.workout.domain.WorkoutId;
import com.fitnessapp.workout.infrastructure.WorkoutRepositoryAdapter;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class WorkoutRepositoryIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.data.redis.host", () -> "localhost");
    }

    @Autowired
    WorkoutRepositoryAdapter repository;

    @Test
    void shouldPersistWorkoutAggregate() {
        Workout workout = new Workout(WorkoutId.newId(), UUID.randomUUID(), "Upper body");
        workout.start();
        Workout saved = repository.save(workout);

        assertEquals(workout.name(), saved.name());
    }
}
