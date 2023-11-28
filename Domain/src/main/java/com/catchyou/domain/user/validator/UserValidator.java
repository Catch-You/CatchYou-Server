package com.catchyou.domain.user.validator;

import com.catchyou.core.annotation.Validator;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.user.adaptor.UserAdaptor;
import com.catchyou.domain.user.exception.UserErrorCode;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class UserValidator {
    private final UserAdaptor userAdaptor;

    public void validateDuplicatedUserEmail(String email) {
        if(userAdaptor.isAlreadyExistUserEmail(email)) {
            throw new BaseException(UserErrorCode.ALREADY_EXIST_EMAIL);
        }
    }

}
