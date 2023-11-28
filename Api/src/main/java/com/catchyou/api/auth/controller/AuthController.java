package com.catchyou.api.auth.controller;

import com.catchyou.api.auth.service.AuthService;
import com.catchyou.api.global.helper.CookieHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.catchyou.core.consts.CatchyouStatic.REFRESH_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final CookieHelper cookieHelper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        AuthResponse authResponse = authService.login(request);
        return ResponseEntity.ok()
                .headers(cookieHelper.getCookies(authResponse))
                .body(authResponse);
    }

    @GetMapping("/login/reissue")
    public ResponseEntity<AuthResponse> reissue(@CookieValue(name = REFRESH_TOKEN) String requestRefreshToken){
        AuthResponse authResponse= authService.reissue(requestRefreshToken);

        return ResponseEntity.ok()
                .headers(cookieHelper.getCookies(authResponse))
                .body(authResponse);
    }
}
