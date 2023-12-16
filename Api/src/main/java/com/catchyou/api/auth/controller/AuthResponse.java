package com.catchyou.api.auth.controller;

import com.catchyou.domain.user.entity.User;
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

    private String userName;

    private Role role;

    public static AuthResponse of(
            String accessToken, String refreshToken, User user, Long accessTokenExpireDate
    ){
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .accessTokenExpireDate(accessTokenExpireDate)
                .userName(user.getName())
                .role(user.getRole())
                .build();
    }
}
