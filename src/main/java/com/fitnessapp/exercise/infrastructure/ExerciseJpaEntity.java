package com.fitnessapp.exercise.infrastructure;

import com.fitnessapp.exercise.domain.ExerciseVisibility;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "exercises")
public class ExerciseJpaEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String category;
    private String muscleGroup;
    private String equipmentType;
    @Enumerated(EnumType.STRING)
    private ExerciseVisibility visibility;

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getMuscleGroup() { return muscleGroup; }
    public String getEquipmentType() { return equipmentType; }
    public ExerciseVisibility getVisibility() { return visibility; }
}
