package com.catchyou.api.auth.controller;

import com.catchyou.domain.user.enums.Role;
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

    private Role role;

    public static AuthResponse of(
            String accessToken, String refreshToken, Long userId, Long accessTokenExpireDate, Role role
    ){
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userId)
                .accessTokenExpireDate(accessTokenExpireDate)
                .role(role)
                .build();
    }
}
