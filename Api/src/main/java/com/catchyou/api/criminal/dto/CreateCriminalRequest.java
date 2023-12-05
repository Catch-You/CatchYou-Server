package com.catchyou.api.criminal.dto;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import com.catchyou.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCriminalRequest {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;   //제목

    @NotBlank(message = "사건 개요는 필수 입력 값입니다.")
    private String summary; //사건 개요

    @NotBlank(message = "인상착의는 필수 입력 값입니다.")
    private String description; //인상착의

    private Region region;  //사건 발생 지역

    private CrimeType crimeType;    //사건 종류

    public Criminal toEntity(User user){
        return Criminal.builder()
                .title(title)
                .user(user)
                .summary(summary)
                .description(description)
                .region(region)
                .crimeType(crimeType)
                .status(Status.N)   //생성 시에는 비공개
                .selectStatus(Status.N) //생성 시에는 확정 몽타주 없음
                .build();
    }
}
