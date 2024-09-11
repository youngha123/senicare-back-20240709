package com.korit.senicare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Response의 공통적 형태

@Getter
@AllArgsConstructor
public class ResponseDto {
    
    private String code;
    private String message;

}
