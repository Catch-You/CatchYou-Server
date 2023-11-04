package com.catchyou.api.signup.service;

import com.catchyou.api.signup.dto.SignupRequest;
import com.catchyou.core.dto.BaseResponse;
import com.catchyou.domain.user.UserRepository;
import com.catchyou.domain.user.adaptor.UserAdaptor;
import com.catchyou.domain.user.entity.User;
import com.catchyou.domain.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserAdaptor userAdaptor;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public BaseResponse<Long> signUp(SignupRequest request) {
        userValidator.validateDuplicatedUserEmail(request.getEmail());
        Long userId = userAdaptor
                .save(request.toEntity(passwordEncoder))
                .getId();
        return BaseResponse.of("회원가입 되었습니다.", userId);
    }

    public BaseResponse checkDuplicatedEmail(String email) {
        userValidator.validateDuplicatedUserEmail(email);
        return BaseResponse.from("가입 가능한 이메일입니다.");
    }
}
