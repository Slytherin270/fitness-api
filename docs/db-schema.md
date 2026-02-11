# Database Schema Design

## Core entities

- `workouts`: aggregate root for workout session, includes status lifecycle, ownership, and timestamps.
- `workout_exercises`: child records attached to workouts.
- `workout_sets`: immutable-ish lifting/training facts for reps and weight.
- `exercises`: catalog with public/private visibility and categorization.
- `user_progress`: aggregated training stats per user, updated asynchronously from workout completion events.

## Index strategy

- `idx_workouts_user_completed` for workout history lookups and analytics windows.
- `idx_workout_exercises_exercise` for exercise-centric analytics.
- `idx_exercises_visibility` for cached public listing query.

## Auditing & Concurrency

- `created_at` / `updated_at` auditing fields.
- `version` column in `workouts` for optimistic locking.


## Additional progress table

- `user_progress` stores `total_workouts`, `total_volume`, `personal_records`, and `last_completed_at` for fast dashboard retrieval.
