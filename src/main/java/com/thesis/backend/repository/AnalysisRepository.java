package com.thesis.backend.repository;

import com.thesis.backend.model.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository يوفر لنا كل الدوال الأساسية (save, findById, findAll, delete)
// بدون الحاجة لكتابة سطر واحد من أوامر SQL!
@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Long> {
    
    // يمكننا إضافة دوال بحث مخصصة هنا إذا احتجنا لاحقاً
    // مثلاً: جلب كل التحليلات المكتملة فقط
    // List<AnalysisEntity> findByStatus(String status);
}