package com.catchyou.api.interview.dto;

import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.montage.entity.Montage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InterviewMontageListDto {
    private Long id;

    public static InterviewMontageListDto of(Montage montage){
        return InterviewMontageListDto.builder()
                .id(montage.getId())
                .build();
    }
}
