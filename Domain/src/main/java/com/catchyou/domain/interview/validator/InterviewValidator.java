package com.catchyou.domain.interview.validator;

import com.catchyou.core.annotation.Validator;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.common.Status;
import com.catchyou.domain.interview.adaptor.InterviewAdaptor;
import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import static com.catchyou.domain.criminal.exception.CriminalErrorCode.CANNOT_CREATE_MONTAGE;
import static com.catchyou.domain.criminal.exception.CriminalErrorCode.NOT_VALID_CRIMINAL_DIRECTOR;
import static com.catchyou.domain.interview.exception.InterviewErrorCode.CANNOT_CREATE_MONTAGE_IN_INTERVIEW;
import static com.catchyou.domain.interview.exception.InterviewErrorCode.NOT_VALID_INTERVIEW_DIRECTOR;

@Validator
@RequiredArgsConstructor
public class InterviewValidator {
    private final InterviewAdaptor interviewAdaptor;

    public void isValidInterviewDirector(Long id, User user){
        Interview interview = interviewAdaptor.findById(id);
        if(!Objects.equals(interview.getCriminal().getDirector(), user))
            throw new BaseException(NOT_VALID_INTERVIEW_DIRECTOR);
    }

    public void isValidCreateInterviewMontage(Interview interview){
        //인터뷰에 대해 선택된 몽타주가 있는지(더 이상 이 인터뷰 과정 통해 재생성 불가능)
        if(interview.getSelectStatus().equals(Status.Y))
            throw new BaseException(CANNOT_CREATE_MONTAGE_IN_INTERVIEW);
    }
}
