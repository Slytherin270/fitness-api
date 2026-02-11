package com.fitnessapp.nutrition.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "nutrition_logs")
public class NutritionLogJpaEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private LocalDate loggedOn;
    @Column(nullable = false)
    private String mealType;
    @Column(nullable = false)
    private int calories;
    @Column(nullable = false)
    private BigDecimal proteinGrams;
    @Column(nullable = false)
    private BigDecimal carbsGrams;
    @Column(nullable = false)
    private BigDecimal fatGrams;
    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public LocalDate getLoggedOn() { return loggedOn; }
    public void setLoggedOn(LocalDate loggedOn) { this.loggedOn = loggedOn; }
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }
    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }
    public BigDecimal getProteinGrams() { return proteinGrams; }
    public void setProteinGrams(BigDecimal proteinGrams) { this.proteinGrams = proteinGrams; }
    public BigDecimal getCarbsGrams() { return carbsGrams; }
    public void setCarbsGrams(BigDecimal carbsGrams) { this.carbsGrams = carbsGrams; }
    public BigDecimal getFatGrams() { return fatGrams; }
    public void setFatGrams(BigDecimal fatGrams) { this.fatGrams = fatGrams; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
