package com.catchyou.api.criminal.dto;

import com.catchyou.api.interview.dto.InterviewMontageListDto;
import com.catchyou.api.interview.dto.SelectInterviewMontageRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DirectorCriminalDetailResponse {
    MyCriminalDetailsDto detailsDto;

    List<InterviewMontageListDto> montages;

    public static DirectorCriminalDetailResponse from(MyCriminalDetailsDto detailsDto,
                                                      List<InterviewMontageListDto> montages){
        return DirectorCriminalDetailResponse.builder()
                .detailsDto(detailsDto)
                .montages(montages)
                .build();
    }
}
