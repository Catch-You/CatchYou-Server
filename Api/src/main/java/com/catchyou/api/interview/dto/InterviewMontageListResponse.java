package com.catchyou.api.interview.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InterviewMontageListResponse {
    List<InterviewMontageListDto> interviewMontageListDtos;

    public static InterviewMontageListResponse from(List<InterviewMontageListDto> interviewMontageListDtos) {
        return InterviewMontageListResponse.builder()
                .interviewMontageListDtos(interviewMontageListDtos)
                .build();
    }
}
