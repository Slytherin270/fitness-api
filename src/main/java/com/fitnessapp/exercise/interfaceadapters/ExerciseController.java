package com.fitnessapp.exercise.interfaceadapters;

import com.fitnessapp.exercise.application.ExerciseQueryService;
import com.fitnessapp.exercise.domain.Exercise;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseQueryService queryService;

    public ExerciseController(ExerciseQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/public")
    public List<Exercise> publicExercises() {
        return queryService.publicExercises();
    }
}
