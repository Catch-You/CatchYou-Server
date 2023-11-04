package com.catchyou.api.signup.dto;

import com.catchyou.domain.user.entity.User;
import com.catchyou.domain.user.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class SignupRequest {

    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력입니다.")
    private String email;

    @NotBlank(message = "실명은 필수 입력입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해야 합니다.")
    private String password;

    @NotBlank(message = "올바른 권한을 입력해주세요.")
    private String role;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .role(Role.valueOf(role))
                .build();
    }
}
