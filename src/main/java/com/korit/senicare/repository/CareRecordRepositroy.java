package com.korit.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.CareRecordEntity;

@Repository
public interface CareRecordRepositroy extends JpaRepository<CareRecordEntity, Integer> {
    
}
