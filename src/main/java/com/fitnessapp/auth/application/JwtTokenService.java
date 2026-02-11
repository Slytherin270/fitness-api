package com.fitnessapp.auth.application;

import com.fitnessapp.auth.domain.UserAccount;

public interface JwtTokenService {
    String issueToken(UserAccount userAccount);
}
