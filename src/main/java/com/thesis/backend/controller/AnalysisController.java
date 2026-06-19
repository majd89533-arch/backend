package com.thesis.backend.controller;

import com.thesis.backend.dto.AnalysisResponse;
import com.thesis.backend.dto.EvaluationRequest;
import com.thesis.backend.dto.TextAnalysisRequest;
import com.thesis.backend.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
// Note: Consider restricting origins in production instead of using "*" 
// e.g., @CrossOrigin(origins = {"http://localhost:3000", "https://yourdomain.com"})
@CrossOrigin(origins = "*") 
public class AnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/full")
    public ResponseEntity<AnalysisResponse> analyzeText(@RequestBody TextAnalysisRequest request) {
        logger.info("Received text analysis request.");
        
        // Processing the text using the mock service method
        AnalysisResponse response = analysisService.processFullAnalysisMock(request.text());
        
        return ResponseEntity.ok(response);
    }

    // 🆕 Endpoint جديد لاستقبال التقييمات
    @PostMapping("/evaluate")
    public ResponseEntity<String> submitEvaluation(@RequestBody EvaluationRequest request) {
        logger.info("Received evaluation submission request.");
        
        // Saving the evaluations
        analysisService.saveEvaluations(request);
        
        return ResponseEntity.ok("Evaluations saved successfully!");
    }
}