package com.catchyou.domain.user.adaptor;

import com.catchyou.core.annotation.Adaptor;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.user.enums.Status;
import com.catchyou.domain.user.repository.UserRepository;
import com.catchyou.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import static com.catchyou.domain.user.exception.UserErrorCode.NOT_FOUND_USER;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new BaseException(NOT_FOUND_USER));
    }

    public boolean isAlreadyExistUserEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByIdAndIsDeleted(Long id, Status isDeleted) {
        return userRepository.findByIdAndIsDeleted(id, isDeleted).orElseThrow(()-> new BaseException(NOT_FOUND_USER));
    }
}
