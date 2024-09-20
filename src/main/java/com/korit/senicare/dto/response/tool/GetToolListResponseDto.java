package com.korit.senicare.dto.response.tool;

import com.korit.senicare.common.object.Tool;
import com.korit.senicare.dto.response.ResponseCode;
import com.korit.senicare.dto.response.ResponseDto;
import com.korit.senicare.dto.response.ResponseMessage;
import com.korit.senicare.entity.ToolEntity;

import lombok.Getter;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetToolListResponseDto extends ResponseDto {
    
    private List<Tool> tools;

    private GetToolListResponseDto(List<ToolEntity> toolEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.tools = Tool.getList(toolEntities);
    }

    public static ResponseEntity<GetToolListResponseDto> success(List<ToolEntity> toolEntities) {
        GetToolListResponseDto responseBody = new GetToolListResponseDto(toolEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
