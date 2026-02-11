package com.fitnessapp.workout.domain;

import java.math.BigDecimal;

public class PersonalRecordPolicy {

    public boolean isNewRecord(BigDecimal historicalBest, BigDecimal newLift) {
        if (newLift == null || newLift.signum() <= 0) {
            return false;
        }
        if (historicalBest == null) {
            return true;
        }
        return newLift.compareTo(historicalBest) > 0;
    }
}
