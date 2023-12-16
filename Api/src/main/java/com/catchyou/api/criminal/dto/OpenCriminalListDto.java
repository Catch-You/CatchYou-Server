package com.catchyou.api.criminal.dto;

import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import com.catchyou.domain.montage.entity.Montage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OpenCriminalListDto {
    private Long id;

    private Long userId;

    private String userName;

    private String title;

    private CrimeType crimeType;

    private Region region;

    private Long montageId;

    public static OpenCriminalListDto of(Criminal criminal, Montage montage){
        return OpenCriminalListDto.builder()
                .id(criminal.getId())
                .userId(criminal.getUser().getId())
                .userName(criminal.getUser().getName())
                .title(criminal.getTitle())
                .crimeType(criminal.getCrimeType())
                .region(criminal.getRegion())
                .montageId(montage.getId())
                .build();
    }
}
