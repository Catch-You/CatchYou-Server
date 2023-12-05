package com.catchyou.api.criminal.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyCriminalListResponse {
    List<MyCriminalListDto> criminalListDtos;

    public static MyCriminalListResponse from(List<MyCriminalListDto> criminalListDtos){
        return MyCriminalListResponse.builder()
                .criminalListDtos(criminalListDtos)
                .build();
    }
}
