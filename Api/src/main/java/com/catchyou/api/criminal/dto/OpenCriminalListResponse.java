package com.catchyou.api.criminal.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OpenCriminalListResponse {
    List<OpenCriminalListDto> openCriminalListDtos;

    public static OpenCriminalListResponse from(List<OpenCriminalListDto> openCriminalListDtos){
        return OpenCriminalListResponse.builder()
                .openCriminalListDtos(openCriminalListDtos)
                .build();
    }
}
