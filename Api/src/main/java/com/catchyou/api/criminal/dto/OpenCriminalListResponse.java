package com.catchyou.api.criminal.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OpenCriminalListResponse {
    List<OpenCriminalListDto> robbery;  //강도

    List<OpenCriminalListDto> murder;  //살인

    List<OpenCriminalListDto> theft;  //절도

    List<OpenCriminalListDto> sexCrime;  //성범죄

    List<OpenCriminalListDto> arson;  //방화

    List<OpenCriminalListDto> fraud;  //사기

    List<OpenCriminalListDto> drug;  //마약

    List<OpenCriminalListDto> gamble;  //도박

    List<OpenCriminalListDto> economicCrime;  //특경법


    public static OpenCriminalListResponse from(List<OpenCriminalListDto> robbery,
                                                List<OpenCriminalListDto> murder,
                                                List<OpenCriminalListDto> theft,
                                                List<OpenCriminalListDto> sexCrime,
                                                List<OpenCriminalListDto> arson,
                                                List<OpenCriminalListDto> fraud,
                                                List<OpenCriminalListDto> drug,
                                                List<OpenCriminalListDto> gamble,
                                                List<OpenCriminalListDto> economicCrime){
        return OpenCriminalListResponse.builder()
                .robbery(robbery)
                .murder(murder)
                .theft(theft)
                .sexCrime(sexCrime)
                .arson(arson)
                .fraud(fraud)
                .drug(drug)
                .gamble(gamble)
                .economicCrime(economicCrime)
                .build();
    }
}
