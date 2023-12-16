package com.catchyou.api.criminal.dto;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import com.catchyou.domain.montage.entity.Montage;
import com.catchyou.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MyCriminalDetailsDto {
    private Long id;    //사건 id

    private Long userId;  //작성자(경찰) 아이디

    private String userName;

    private String title;   //제목

    private String summary; //사건 개요

    private String description; //인상착의

    private String criminalCode;    //사건 코드

    private Region region;  //사건 발생 지역

    private CrimeType crimeType;    //사건 종류

    private Status status;  //Y면 공개, N이면 비공개

    private Status selectStatus;    //몽타주 작성 완료 : Y면 완료, N이면 미완료

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //여기에 몽타주 이미지, 몽타주 작성자 추가
    private Long montageId;

    private Long directorId;

    private String directorName;

    public static MyCriminalDetailsDto of(Criminal criminal, Montage montage){
        return MyCriminalDetailsDto.builder()
                .id(criminal.getId())
                .userId(criminal.getUser().getId())
                .userName(criminal.getUser().getName())
                .title(criminal.getTitle())
                .summary(criminal.getSummary())
                .description(criminal.getDescription())
                .criminalCode(criminal.getCriminalCode())
                .region(criminal.getRegion())
                .crimeType(criminal.getCrimeType())
                .status(criminal.getStatus())
                .selectStatus(criminal.getSelectStatus())
                .createdAt(criminal.getCreatedAt())
                .updatedAt(criminal.getUpdatedAt())
                .montageId(montage == null ? null : montage.getId())
                .directorId(criminal.getDirector() == null? null: criminal.getDirector().getId())
                .directorName(criminal.getDirector().getName())
                .build();
    }
}
