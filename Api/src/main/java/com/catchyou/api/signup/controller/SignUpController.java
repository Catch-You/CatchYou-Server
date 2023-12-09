package com.catchyou.api.signup.controller;

import com.catchyou.api.signup.dto.SignupRequest;
import com.catchyou.api.signup.service.MailService;
import com.catchyou.api.signup.service.SignupService;
import com.catchyou.core.dto.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signup")
public class SignUpController {
    private final SignupService signupService;

    private final MailService mailService;

    //회원가입
    @PostMapping
    public BaseResponse<Long> signUp(@RequestBody @Valid SignupRequest request) {
        return signupService.signUp(request);
    }

    //중복 이메일 검사
    @GetMapping("/email-check/{email}")
    public BaseResponse checkDuplicatedEmail(@PathVariable String email) {
        return signupService.checkDuplicatedEmail(email);
    }

    //이메일 인증
    @GetMapping("/mail-confirm/{email}")
    public BaseResponse<String> mailConfirm(@PathVariable String email) throws Exception {
        return mailService.mailConfirm(email);
    }

}
