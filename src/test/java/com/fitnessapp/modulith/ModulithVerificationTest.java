package com.fitnessapp.modulith;

import com.fitnessapp.FitnessApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithVerificationTest {

    @Test
    void shouldVerifyModuleBoundaries() {
        ApplicationModules.of(FitnessApiApplication.class).verify();
    }
}
