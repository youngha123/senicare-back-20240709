package com.korit.senicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.senicare.dto.request.nurse.PatchNurseRequestDto;
import com.korit.senicare.dto.response.ResponseDto;
import com.korit.senicare.dto.response.nurse.GetChargedCustomerResponseDto;
import com.korit.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.korit.senicare.dto.response.nurse.GetNurseResponseDto;
import com.korit.senicare.dto.response.nurse.GetSignInResponseDto;
import com.korit.senicare.service.NurseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/nurse")
@RequiredArgsConstructor
public class NurseController {

    private final NurseService nurseService;

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetNurseListResponseDto> getNurseList() {
        ResponseEntity<? super GetNurseListResponseDto> response = nurseService.getNurseList();
        return response;
    }

    @GetMapping("/sign-in")
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetSignInResponseDto> response = nurseService.getSignIn(userId);
        return response;
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<? super GetNurseResponseDto> getNurse(
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetNurseResponseDto> response = nurseService.getNurse(userId);
        return response;
    }

    @PatchMapping(value={"", "/ "})
    public ResponseEntity<ResponseDto> patchNurse (
        @RequestBody @Valid PatchNurseRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = nurseService.patchNurse(requestBody, userId);
        return response;
    }

    @GetMapping("/{nurseId}/customers")
    public ResponseEntity<? super GetChargedCustomerResponseDto> getChargedCustomer (
        @PathVariable("nurseId") String nurseId
    ) {
        ResponseEntity<? super GetChargedCustomerResponseDto> response = nurseService.getChargedCustomer(nurseId);
        return response;
    }

}

