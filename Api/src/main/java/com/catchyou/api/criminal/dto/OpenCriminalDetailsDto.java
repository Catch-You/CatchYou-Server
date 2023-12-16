package com.catchyou.api.criminal.dto;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import com.catchyou.domain.montage.entity.Montage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OpenCriminalDetailsDto {
    private Long id;    //사건 아이디

    private Long userId;    //경찰 아이디

    private String userName;    //경찰 이름

    private String title;

    private String summary; //사건 개요

    private String description;

    private Region region;  //사건 발생 지역

    private CrimeType crimeType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long montageId;

    public static OpenCriminalDetailsDto of(Criminal criminal, Montage montage){
        return OpenCriminalDetailsDto.builder()
                .id(criminal.getId())
                .userId(criminal.getUser().getId())
                .userName(criminal.getUser().getName())
                .title(criminal.getTitle())
                .summary(criminal.getSummary())
                .description(criminal.getDescription())
                .region(criminal.getRegion())
                .crimeType(criminal.getCrimeType())
                .createdAt(criminal.getCreatedAt())
                .updatedAt(criminal.getUpdatedAt())
                .montageId(montage.getId())
                .build();
    }
}
