package com.korit.senicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.korit.senicare.dto.request.customer.PostCareRecordRequestDto;
import com.korit.senicare.dto.request.customer.PostCustomerRequestDto;
import com.korit.senicare.dto.response.ResponseDto;
import com.korit.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.korit.senicare.dto.response.customer.GetCustomerResponseDto;
import com.korit.senicare.entity.CareRecordEntity;
import com.korit.senicare.entity.CustomerEntity;
import com.korit.senicare.entity.ToolEntity;
import com.korit.senicare.repository.CareRecordRepositroy;
import com.korit.senicare.repository.CustomerRepository;
import com.korit.senicare.repository.NurseRepository;
import com.korit.senicare.repository.ToolRepository;
import com.korit.senicare.repository.resultSet.GetCustomerResultSet;
import com.korit.senicare.repository.resultSet.GetCustomersResultSet;
import com.korit.senicare.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {

    private final ToolRepository toolRepository;
    private final NurseRepository nurseRepository;
    private final CustomerRepository customerRepository;
    private final CareRecordRepositroy careRecordRepository;

    @Override
    public ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto dto) {

        try {
            
            String charger = dto.getCharger();
            boolean isExistedNurse = nurseRepository.existsByUserId(charger);
            if (!isExistedNurse) return ResponseDto.noExistUserId();

            CustomerEntity customerEntity = new CustomerEntity(dto);
            customerRepository.save(customerEntity);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetCustomerListResponseDto> getCustmerList() {

        List<GetCustomersResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = customerRepository.getCustomers();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCustomerListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer customerNumber) {
        
        GetCustomerResultSet resultSet = null;

        try {

            resultSet = customerRepository.getCustomer(customerNumber);
            if (resultSet == null) return ResponseDto.noExistCustomer();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCustomerResponseDto.success(resultSet);

    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(
        PatchCustomerRequestDto dto, 
        Integer customerNumber, 
        String userId
    ) {
        
        try {

            CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
            if (customerEntity == null) return ResponseDto.noExistCustomer();

            String charger = customerEntity.getCharger();
            boolean isCharger = charger.equals(userId);
            if (!isCharger) return ResponseDto.noPermission();

            customerEntity.patch(dto);
            customerRepository.save(customerEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteCustomer(Integer customerNumber, String userId) {
        
        try {
            
            CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
            if (customerEntity == null) return ResponseDto.noExistCustomer();

            String charger = customerEntity.getCharger();
            boolean isCharger = charger.equals(userId);
            if (!isCharger) return ResponseDto.noPermission();

            careRecordRepository.deleteByCustomerNumber(customerNumber);
            customerRepository.delete(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
        
    }

    @Override
    public ResponseEntity<ResponseDto> postCareRecord(
        PostCareRecordRequestDto dto, 
        Integer customerNumber,
        String userId
    ) {

        try {
        
            String usedToolName = null;

            Integer usedToolNumber = dto.getUsedToolNumber();
            if (usedToolNumber != null) {
                ToolEntity toolEntity = toolRepository.findByToolNumber(usedToolNumber);
                if (toolEntity == null) return ResponseDto.noExistCustomer();

                Integer count = toolEntity.getCount();
                Integer usedCount = dto.getCount();
                if (usedCount > count) return ResponseDto.toolInsufficient(); 

                usedToolName = toolEntity.getName();
            }

            CareRecordEntity careRecordEntity = new CareRecordEntity(dto, usedToolName, userId, customerNumber);
            careRecordRepository.save(careRecordEntity);

            if (usedToolNumber != null) {
                
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }
    
}
