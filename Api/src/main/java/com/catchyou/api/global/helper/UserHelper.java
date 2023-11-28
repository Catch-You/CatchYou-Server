package com.catchyou.api.global.helper;

import com.catchyou.api.config.security.SecurityUtils;
import com.catchyou.core.annotation.Helper;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.user.entity.User;
import com.catchyou.domain.user.enums.Status;
import com.catchyou.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import static com.catchyou.domain.user.exception.UserErrorCode.NOT_FOUND_USER;

@Helper
@RequiredArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;

    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserId();
    }

    public User getCurrentUser() {
        return userRepository
                .findByIdAndIsDeleted(getCurrentUserId(), Status.N)
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));
    }
}
