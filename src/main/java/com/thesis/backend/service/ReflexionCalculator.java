package com.thesis.backend.service;

import org.springframework.stereotype.Service;

@Service
public class ReflexionCalculator {

    /**
     * معادلة ليفبفر الأساسية (لحساب قرار المستخدم)
     * F(x, y) = 1.0 - y * (1.0 - x)
     * * @param x الرسالة أو المُحفز (من 0.0 إلى 1.0)
     * @param y صورة المستخدم أو الفلتر (من 0.0 إلى 1.0)
     * @return قرار المستخدم النهائي (هل سيقتنع أم لا)
     */
    public double calculateDecision(double x, double y) {
        if (x < 0 || x > 1 || y < 0 || y > 1) {
            throw new IllegalArgumentException("يجب أن تكون الأرقام بين 0.0 و 1.0");
        }
        return 1.0 - y * (1.0 - x);
    }

    /**
     * معادلة ليفبفر العكسية (Manipulation Planner)
     * تستخدم لحساب "الرسالة المطلوبة" للوصول إلى هدف معين.
     * x1 = x2 / ((1/x3) - 1 + x2)
     * * @param targetX3 الهدف الذي نريد الوصول إليه (مثلاً 0.8 أي إقناع بنسبة 80%)
     * @param currentX2 شخصية المستخدم الحالية (تقييمه في إحدى صفات Big Five)
     * @return الرسالة المطلوبة (x1) التي سنرسلها لاحقاً للـ LLM
     */
    public double calculateRequiredMessage(double targetX3, double currentX2) {
        // حماية رياضية من القسمة على صفر
        if (targetX3 <= 0.0) return 0.0; 
        
        return currentX2 / ((1.0 / targetX3) - 1.0 + currentX2);
    }
}