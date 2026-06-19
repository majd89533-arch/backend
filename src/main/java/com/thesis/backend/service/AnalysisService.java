
package com.thesis.backend.service;


import com.thesis.backend.dto.AnalysisResponse;

import com.thesis.backend.dto.EvaluationRequest;

import com.thesis.backend.dto.OceanScores;

import com.thesis.backend.model.AnalysisEntity;

import com.thesis.backend.repository.AnalysisRepository;

import org.springframework.stereotype.Service;


@Service

public class AnalysisService {


private final ReflexionCalculator reflexionCalculator;

private final AnalysisRepository analysisRepository;


public AnalysisService(ReflexionCalculator reflexionCalculator, AnalysisRepository analysisRepository) {

this.reflexionCalculator = reflexionCalculator;

this.analysisRepository = analysisRepository;

}


public AnalysisResponse processFullAnalysisMock(String text) {

OceanScores mockScores = new OceanScores(0.85, 0.70, 0.40, 0.90, 0.30, "Mock Reasoning");

double targetShift = reflexionCalculator.calculateRequiredMessage(0.8, mockScores.extraversion());

String mockGeneratedAd = "لأنك شخص تفضل الهدوء والتركيز، هذه السماعات صُممت لك.";

String mockGenericAd = "اشترِ سماعاتنا الجديدة الآن جودة عالية تناسب الجميع.";


AnalysisEntity entity = new AnalysisEntity();

entity.setInputText(text);

entity.setStatus("STARTED"); // غيرناها لـ STARTED، ستصبح COMPLETED بعد التقييم

entity.setOceanOpenness(mockScores.openness());

entity.setOceanConscientiousness(mockScores.conscientiousness());

entity.setOceanExtraversion(mockScores.extraversion());

entity.setOceanAgreeableness(mockScores.agreeableness());

entity.setOceanNeuroticism(mockScores.neuroticism());

entity.setOceanReasoning(mockScores.reasoning());

entity.setCalculatedTargetShift(targetShift);

entity.setGeneratedAdText(mockGeneratedAd);

entity.setGenericAdText(mockGenericAd);


// نحفظ في الداتا بيز ليتم توليد الـ ID

entity = analysisRepository.save(entity);

// 🆕 نرسل الـ entity.getId() مع الرد

return new AnalysisResponse(entity.getId(), mockScores, targetShift, mockGeneratedAd, mockGenericAd);

}


// 🆕 دالة جديدة لحفظ التقييمات

public void saveEvaluations(EvaluationRequest request) {

// نبحث عن التحليل باستخدام الـ ID

AnalysisEntity entity = analysisRepository.findById(request.analysisId())

.orElseThrow(() -> new RuntimeException("Analysis not found with ID: " + request.analysisId()));


// نحدث الحقول بالبيانات القادمة من React

entity.setCustomPersuasion(request.customPersuasion());

entity.setCustomRelevance(request.customRelevance());

entity.setCustomInterest(request.customInterest());

entity.setCustomManipulation(request.customManipulation());


entity.setGenericPersuasion(request.genericPersuasion());

entity.setGenericRelevance(request.genericRelevance());

entity.setGenericInterest(request.genericInterest());

entity.setGenericManipulation(request.genericManipulation());


// نحدث الحالة إلى مكتمل (لأنه أنهى قراءة الإعلان وقيمه)

entity.setStatus("EVALUATED");


// نحفظ التحديث في قاعدة البيانات

analysisRepository.save(entity);

System.out.println("✅ تم حفظ التقييمات بنجاح للـ ID: " + entity.getId());

}

} 