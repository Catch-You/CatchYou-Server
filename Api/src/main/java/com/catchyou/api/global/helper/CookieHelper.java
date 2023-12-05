package com.catchyou.api.global.helper;

import com.catchyou.api.auth.controller.AuthResponse;
import com.catchyou.core.annotation.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import static com.catchyou.core.consts.CatchyouStatic.*;

@Helper
@RequiredArgsConstructor
public class CookieHelper {

    public HttpHeaders getCookies(AuthResponse authResponse) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, authResponse.getRefreshToken())
                .path("/")
                .sameSite(SAMESITE_NONE)
                .httpOnly(true)
                .secure(false)  //일단 이렇게
                .maxAge(COOKIE_EXPIRATION)
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, authResponse.getAccessToken());
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());
        return httpHeaders;
    }

    public HttpHeaders deleteCookies(){
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, null)
                .path("/")
                .sameSite(SAMESITE_NONE)
                .httpOnly(true)
                .secure(true)
                .maxAge(0)
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());
        return httpHeaders;
    }
}
