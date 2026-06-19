package com.thesis.backend.dto;

// أضفنا id هنا لكي يعرف React رقم التحليل الخاص به
public record AnalysisResponse(
    Long id, 
    OceanScores oceanScores,
    double calculatedTargetShift,
    String generatedAd,
    String genericAd
) {}