package com.korit.senicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.CareRecordEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CareRecordRepositroy extends JpaRepository<CareRecordEntity, Integer> {
    
    List<CareRecordEntity> findByCustomerNumberOrderByRecordNumberDesc(Integer customerNumber);

    @Transactional
    void deleteByCustomerNumber(Integer customerNumber);

}
