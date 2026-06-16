package com.thesis.backend.controller;

import com.thesis.backend.service.ReflexionCalculator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reflexion")
@CrossOrigin(origins = "*") // السماح لـ React بالاتصال بهذا الخادم لاحقاً
public class ReflexionController {

    private final ReflexionCalculator calculator;

    // حقن (Injection) لخدمة الحساب التي صنعناها في الخطوة السابقة
    public ReflexionController(ReflexionCalculator calculator) {
        this.calculator = calculator;
    }

    // ---------------------------------------------------------
    // DTOs (Data Transfer Objects) - حاويات صغيرة لاستقبال البيانات
    // نستخدم ميزة 'record' في جافا الحديثة لاختصار الكود
    // ---------------------------------------------------------
    public record DecisionRequest(double x, double y) {}
    public record ManipulationRequest(double targetX3, double currentX2) {}

    // ---------------------------------------------------------
    // Endpoints (الروابط التي سيكلمنا React من خلالها)
    // ---------------------------------------------------------

    // الرابط الأول: حساب القرار
    // POST: http://localhost:8080/api/reflexion/decision
    @PostMapping("/decision")
    public double getDecision(@RequestBody DecisionRequest request) {
        return calculator.calculateDecision(request.x(), request.y());
    }

    // الرابط الثاني: حساب الرسالة المطلوبة (Manipulation Planner)
    // POST: http://localhost:8080/api/reflexion/manipulation
    @PostMapping("/manipulation")
    public double getRequiredMessage(@RequestBody ManipulationRequest request) {
        return calculator.calculateRequiredMessage(request.targetX3(), request.currentX2());
    }
}