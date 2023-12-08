package com.catchyou.domain.criminal.validator;

import com.catchyou.core.annotation.Validator;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.criminal.adaptor.CriminalAdaptor;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import static com.catchyou.domain.criminal.exception.CriminalErrorCode.NOT_VALID_CRIMINAL_USER;

@Validator
@RequiredArgsConstructor
public class CriminalValidator {
    private final CriminalAdaptor criminalAdaptor;

    public void isValidCriminalUser(Criminal criminal, User user){
        if(!Objects.equals(criminal.getUser(), user))
            throw new BaseException(NOT_VALID_CRIMINAL_USER);
    }

    //이미 등록된 몽타주 제작자가 있는지 확인
    public boolean alreadyExistDirector(Criminal criminal){
        return criminal.getDirector() != null;
    }
}
