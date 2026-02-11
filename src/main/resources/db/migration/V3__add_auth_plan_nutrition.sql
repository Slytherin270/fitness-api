create table app_users (
    id uuid primary key,
    email varchar(320) not null unique,
    password_hash varchar(255) not null,
    display_name varchar(120) not null,
    created_at timestamp default now()
);

create table plans (
    id uuid primary key,
    user_id uuid not null,
    name varchar(255) not null,
    goal varchar(100),
    active boolean not null default true,
    created_at timestamp default now(),
    updated_at timestamp default now()
);

create table nutrition_logs (
    id uuid primary key,
    user_id uuid not null,
    logged_on date not null,
    meal_type varchar(40) not null,
    calories int not null,
    protein_grams numeric(6,2) not null,
    carbs_grams numeric(6,2) not null,
    fat_grams numeric(6,2) not null,
    created_at timestamp default now()
);

create index idx_plans_user_active on plans(user_id, active);
create index idx_nutrition_logs_user_day on nutrition_logs(user_id, logged_on);
