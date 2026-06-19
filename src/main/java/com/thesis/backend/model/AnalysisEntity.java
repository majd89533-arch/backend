package com.thesis.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "analyses")
@Getter
@Setter
public class AnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_text", columnDefinition = "TEXT", nullable = false)
    private String inputText;

    @Column(name = "ocean_openness") private Double oceanOpenness;
    @Column(name = "ocean_conscientiousness") private Double oceanConscientiousness;
    @Column(name = "ocean_extraversion") private Double oceanExtraversion;
    @Column(name = "ocean_agreeableness") private Double oceanAgreeableness;
    @Column(name = "ocean_neuroticism") private Double oceanNeuroticism;
    @Column(name = "ocean_reasoning", columnDefinition = "TEXT") private String oceanReasoning;

    @Column(name = "calculated_target_shift") private Double calculatedTargetShift;
    @Column(name = "generated_ad_text", columnDefinition = "TEXT") private String generatedAdText;
    @Column(name = "generic_ad_text", columnDefinition = "TEXT") private String genericAdText;

    // ==========================================
    // 🆕 حقول التقييم الجديدة (Likert Scale 1-7)
    // ==========================================
    @Column(name = "custom_persuasion") private Integer customPersuasion;
    @Column(name = "custom_relevance") private Integer customRelevance;
    @Column(name = "custom_interest") private Integer customInterest;
    @Column(name = "custom_manipulation") private Integer customManipulation;

    @Column(name = "generic_persuasion") private Integer genericPersuasion;
    @Column(name = "generic_relevance") private Integer genericRelevance;
    @Column(name = "generic_interest") private Integer genericInterest;
    @Column(name = "generic_manipulation") private Integer genericManipulation;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public AnalysisEntity() {}
}