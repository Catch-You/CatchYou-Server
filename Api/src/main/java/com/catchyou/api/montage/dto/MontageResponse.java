package com.catchyou.api.montage.dto;

import com.catchyou.domain.montage.entity.Montage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MontageResponse {
    private Long id;

    private String prompt;

    public static MontageResponse of(String prompt, Montage montage){
        return MontageResponse.builder()
                .id(montage.getId())
                .prompt(prompt)
                .build();
    }
}
