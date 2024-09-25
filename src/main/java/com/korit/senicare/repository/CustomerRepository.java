package com.korit.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    SELECT 
        C.customer_number as ~~, 
        C.name as ~~, 
        C.birth as ~~, 
        C.location as ~~, 
        N.name as ~~, 
        N.user_id as ~~
    FROM customers C LEFT JOIN nurses N
    ON C.charger = N.user_id
    ORDER BY C.customer_number DESC
    
}
