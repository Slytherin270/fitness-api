package com.fitnessapp.exercise.domain;

import java.util.UUID;

public record Exercise(UUID id, String name, String category, String muscleGroup, String equipmentType, ExerciseVisibility visibility) {
}
