package com.fitnessapp.exercise;

import static org.mockito.Mockito.*;

import com.fitnessapp.exercise.application.ExerciseQueryService;
import com.fitnessapp.exercise.application.ExerciseRepository;
import com.fitnessapp.exercise.domain.Exercise;
import com.fitnessapp.exercise.domain.ExerciseVisibility;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class ExerciseCacheIT {

    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:7.4").withExposedPorts(6379);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redis::getHost);
        registry.add("spring.data.redis.port", () -> redis.getMappedPort(6379));
    }

    @Autowired
    ExerciseQueryService service;

    @MockBean
    ExerciseRepository repository;

    @Test
    void shouldUseCacheForPublicExercises() {
        when(repository.findPublicExercises()).thenReturn(List.of(
                new Exercise(UUID.randomUUID(), "Bench Press", "strength", "chest", "barbell", ExerciseVisibility.PUBLIC)
        ));

        service.publicExercises();
        service.publicExercises();

        verify(repository, times(1)).findPublicExercises();
    }
}
