create table user_progress (
    user_id uuid primary key,
    total_workouts bigint not null default 0,
    total_volume numeric(14,2) not null default 0,
    personal_records bigint not null default 0,
    last_completed_at timestamp
);

create index idx_user_progress_last_completed_at on user_progress(last_completed_at);
