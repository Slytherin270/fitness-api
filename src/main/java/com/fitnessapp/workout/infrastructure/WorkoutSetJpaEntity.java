package com.fitnessapp.workout.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "workout_sets")
public class WorkoutSetJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int reps;
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExerciseJpaEntity workoutExercise;

    public Long getId() { return id; }
    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }
    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
    public WorkoutExerciseJpaEntity getWorkoutExercise() { return workoutExercise; }
    public void setWorkoutExercise(WorkoutExerciseJpaEntity workoutExercise) { this.workoutExercise = workoutExercise; }
}
