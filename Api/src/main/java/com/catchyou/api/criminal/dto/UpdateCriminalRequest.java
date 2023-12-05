package com.catchyou.api.criminal.dto;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import com.catchyou.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCriminalRequest {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;   //제목

    @NotBlank(message = "사건 개요는 필수 입력 값입니다.")
    private String summary; //사건 개요

    @NotBlank(message = "인상착의는 필수 입력 값입니다.")
    private String description; //인상착의

    private Region region;  //사건 발생 지역

    private CrimeType crimeType;    //사건 종류

    private Status status;
}
