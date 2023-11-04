package com.catchyou.api.signup.controller;

import com.catchyou.api.signup.dto.SignupRequest;
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

    @PostMapping
    public BaseResponse<Long> signUp(@RequestBody @Valid SignupRequest request) {
        return signupService.signUp(request);
    }

    @GetMapping("/email-check/{email}")
    public BaseResponse checkDuplicatedEmail(@PathVariable String email) {
        return signupService.checkDuplicatedEmail(email);
    }
}
