package com.korit.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.CareRecordEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CareRecordRepositroy extends JpaRepository<CareRecordEntity, Integer> {
    
    @Transactional
    void deleteByCustomNumber(Integer customerNumber);

}
