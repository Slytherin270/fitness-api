package com.fitnessapp.auth.application;

import com.fitnessapp.auth.domain.UserAccount;
import java.util.Optional;

public interface AuthRepository {
    Optional<UserAccount> findByEmail(String email);

    UserAccount save(UserAccount userAccount);
}
