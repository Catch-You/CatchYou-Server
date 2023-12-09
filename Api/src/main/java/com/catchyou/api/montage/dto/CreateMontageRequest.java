package com.catchyou.api.montage.dto;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.montage.entity.Montage;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMontageRequest {
    @NotBlank(message = "묘사는 필수 입력 값입니다.")
    private String prompt; //묘사 프롬포트

    public Montage toEntity(Interview interview){
        return Montage.builder()
                .interview(interview)
                .selected(Status.N)
                .build();
    }
}
