package com.fitnessapp.workout.infrastructure;

import com.fitnessapp.workout.application.WorkoutRecordGateway;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class WorkoutRecordGatewayAdapter implements WorkoutRecordGateway {

    private final JdbcClient jdbcClient;

    public WorkoutRecordGatewayAdapter(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<BigDecimal> bestLift(UUID userId, UUID exerciseId) {
        return jdbcClient.sql("""
                select max(ws.weight)
                from workouts w
                join workout_exercises we on w.id = we.workout_id
                join workout_sets ws on ws.workout_exercise_id = we.id
                where w.user_id = :userId and we.exercise_id = :exerciseId
                """)
                .param("userId", userId)
                .param("exerciseId", exerciseId)
                .query(BigDecimal.class)
                .optional();
    }
}
