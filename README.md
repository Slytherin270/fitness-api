# Fitness API (Production-Grade Skeleton)

## High-level architecture

- **Clean Architecture** with strict layers:
  - `domain`: pure business model, value objects, domain policies.
  - `application`: use-cases, ports, transaction boundaries, orchestration.
  - `infrastructure`: JPA/Redis adapters, persistence mapping, external gateways.
  - `interfaceadapters`: REST controllers, request/response DTOs, API mappings.
- **Spring Modulith** enforces module boundaries and event-driven collaboration.

## Business modules

- `user`, `auth`, `workout`, `exercise`, `plan`, `progress`, `nutrition`, `analytics`, `notification`
- Example event collaboration implemented:
  - `workout` publishes `WorkoutCompletedEvent`
  - `notification` consumes event using `@ApplicationModuleListener`
  - `progress` consumes event and aggregates user training metrics

## Production concerns

- JWT resource-server setup and role-ready security config
- Validation and global exception handling (`ProblemDetail`)
- Redis cache for public exercise list (`publicExercises`)
- Optimistic locking on workouts (`@Version`)
- Idempotency key interceptor for POST requests
- Basic rate-limiting filter (IP-based)
- PostgreSQL indexing strategy (see migration)
- OpenAPI docs via springdoc
- Progress API (`GET /api/progress/me`) for per-user workout totals, volume, and PR count

## TDD workflow example

1. Write domain and use-case tests (`WorkoutDomainTest`, `WorkoutUseCaseTest`)
2. Implement domain model and use-case orchestration
3. Add repository/integration tests (`WorkoutRepositoryIT`, `ExerciseCacheIT`)
4. Add security test (`SecurityConfigTest`)
5. Verify module boundaries (`ModulithVerificationTest`)

## Run

```bash
docker compose up --build
```

OpenAPI: `http://localhost:8080/swagger-ui.html`
