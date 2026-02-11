package com.fitnessapp.workout.infrastructure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExerciseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID exerciseId;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutJpaEntity workout;

    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutSetJpaEntity> sets = new ArrayList<>();

    public Long getId() { return id; }
    public UUID getExerciseId() { return exerciseId; }
    public void setExerciseId(UUID exerciseId) { this.exerciseId = exerciseId; }
    public WorkoutJpaEntity getWorkout() { return workout; }
    public void setWorkout(WorkoutJpaEntity workout) { this.workout = workout; }
    public List<WorkoutSetJpaEntity> getSets() { return sets; }
    public void setSets(List<WorkoutSetJpaEntity> sets) { this.sets = sets; }
}
