package com.thesis.backend.dto;

// حاوية لاستقبال التقييمات الـ 8 من React
public record EvaluationRequest(
    Long analysisId,
    int customPersuasion,
    int customRelevance,
    int customInterest,
    int customManipulation,
    int genericPersuasion,
    int genericRelevance,
    int genericInterest,
    int genericManipulation
) {}