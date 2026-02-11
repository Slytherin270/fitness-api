package com.fitnessapp.nutrition.infrastructure;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataNutritionRepository extends JpaRepository<NutritionLogJpaEntity, UUID> {
    List<NutritionLogJpaEntity> findByUserIdAndLoggedOn(UUID userId, LocalDate loggedOn);

    @Query("select coalesce(sum(n.calories),0) from NutritionLogJpaEntity n where n.userId = :userId and n.loggedOn between :fromInclusive and :toInclusive")
    int totalCalories(@Param("userId") UUID userId,
                      @Param("fromInclusive") LocalDate fromInclusive,
                      @Param("toInclusive") LocalDate toInclusive);
}
