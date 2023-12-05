package com.catchyou.api.criminal.dto;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import com.catchyou.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyCriminalListDto {
    private Long id;

    private Long userId;

    private String title;   //제목

    private CrimeType crimeType;    //사건 종류

    private Status status;  //Y면 공개, N이면 비공개

    private Status selectStatus;    //몽타주 확정 상태

    public static MyCriminalListDto of(Criminal criminal){
        return MyCriminalListDto.builder()
                .id(criminal.getId())
                .userId(criminal.getUser().getId())
                .title(criminal.getTitle())
                .crimeType(criminal.getCrimeType())
                .status(criminal.getStatus())
                .selectStatus(criminal.getSelectStatus())
                .build();
    }
}
