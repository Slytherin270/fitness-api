package com.fitnessapp.auth.domain;

import java.time.Instant;
import java.util.UUID;

public record UserAccount(UUID id, String email, String passwordHash, String displayName, Instant createdAt) {}
