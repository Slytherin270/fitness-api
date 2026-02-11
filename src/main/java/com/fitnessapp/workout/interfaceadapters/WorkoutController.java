package com.fitnessapp.workout.interfaceadapters;

import com.fitnessapp.workout.application.WorkoutUseCase;
import com.fitnessapp.workout.application.commands.AddExerciseCommand;
import com.fitnessapp.workout.application.commands.AddSetCommand;
import com.fitnessapp.workout.application.commands.CompleteWorkoutCommand;
import com.fitnessapp.workout.application.commands.CreateWorkoutCommand;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutUseCase workoutUseCase;
    private final WorkoutResponseMapper mapper;

    public WorkoutController(WorkoutUseCase workoutUseCase, WorkoutResponseMapper mapper) {
        this.workoutUseCase = workoutUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public WorkoutDtos.WorkoutResponse create(@AuthenticationPrincipal Jwt jwt,
                                              @Valid @RequestBody WorkoutDtos.CreateWorkoutRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return mapper.toResponse(workoutUseCase.create(new CreateWorkoutCommand(userId, request.name())));
    }

    @PatchMapping("/{workoutId}/exercises")
    public WorkoutDtos.WorkoutResponse addExercise(@PathVariable UUID workoutId,
                                                   @Valid @RequestBody WorkoutDtos.AddExerciseRequest request) {
        return mapper.toResponse(workoutUseCase.addExercise(new AddExerciseCommand(workoutId, request.exerciseId())));
    }

    @PatchMapping("/{workoutId}/sets")
    public WorkoutDtos.WorkoutResponse addSet(@PathVariable UUID workoutId,
                                              @Valid @RequestBody WorkoutDtos.AddSetRequest request) {
        return mapper.toResponse(workoutUseCase.addSet(new AddSetCommand(workoutId, request.exerciseId(), request.reps(), request.weight())));
    }

    @PatchMapping("/{workoutId}/complete")
    public WorkoutDtos.WorkoutResponse complete(@PathVariable UUID workoutId) {
        return mapper.toResponse(workoutUseCase.complete(new CompleteWorkoutCommand(workoutId)));
    }

    @GetMapping
    public Page<WorkoutDtos.WorkoutResponse> history(@AuthenticationPrincipal Jwt jwt, Pageable pageable) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return workoutUseCase.history(userId, pageable).map(mapper::toResponse);
    }
}
