package com.korit.senicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.senicare.dto.request.auth.IdCheckRequestDto;
import com.korit.senicare.dto.response.ResponseDto;
import com.korit.senicare.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
        return response;
    }
    
}
