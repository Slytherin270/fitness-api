create table workouts (
    id uuid primary key,
    user_id uuid not null,
    name varchar(255) not null,
    status varchar(50) not null,
    started_at timestamp,
    completed_at timestamp,
    version bigint default 0,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

create table workout_exercises (
    id bigserial primary key,
    workout_id uuid not null references workouts(id) on delete cascade,
    exercise_id uuid not null
);

create table workout_sets (
    id bigserial primary key,
    workout_exercise_id bigint not null references workout_exercises(id) on delete cascade,
    reps int not null,
    weight numeric(8,2) not null
);

create table exercises (
    id uuid primary key,
    name varchar(255) not null,
    category varchar(50),
    muscle_group varchar(50),
    equipment_type varchar(50),
    visibility varchar(20) not null,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

create index idx_workouts_user_completed on workouts(user_id, completed_at);
create index idx_workout_exercises_exercise on workout_exercises(exercise_id);
create index idx_exercises_visibility on exercises(visibility);
