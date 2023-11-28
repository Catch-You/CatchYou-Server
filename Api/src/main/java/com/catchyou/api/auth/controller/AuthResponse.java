package com.catchyou.api.auth.controller;

import lombok.Builder;
import lombok.Getter;

import static com.catchyou.core.consts.CatchyouStatic.BEARER;

@Getter
@Builder
public class AuthResponse {

    private String accessToken;

    private String refreshToken;

    private Long userId;

    private Long accessTokenExpireDate;

    public static AuthResponse of(
            String accessToken, String refreshToken, Long userId, Long accessTokenExpireDate
    ){
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userId)
                .accessTokenExpireDate(accessTokenExpireDate)
                .build();
    }
}
