package com.thesis.backend.dto;

// حاوية لدرجات الشخصية
public record OceanScores(
    double openness,
    double conscientiousness,
    double extraversion,
    double agreeableness,
    double neuroticism,
    String reasoning
) {}