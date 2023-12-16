package com.catchyou.api.criminal.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DirectorCriminalListResponse {
    List<MyCriminalListDto> criminalListDtos;
    List<MyCriminalListDto> confirmedCriminalListDtos;

    public static DirectorCriminalListResponse from(List<MyCriminalListDto> criminalListDtos,
                                                    List<MyCriminalListDto> confirmedCriminalListDtos){
        return DirectorCriminalListResponse.builder()
                .criminalListDtos(criminalListDtos)
                .confirmedCriminalListDtos(confirmedCriminalListDtos)
                .build();
    }
}
