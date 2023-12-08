package com.catchyou.domain.interview.adaptor;

import com.catchyou.core.annotation.Adaptor;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.interview.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.catchyou.domain.interview.exception.InterviewErrorCode.NOT_FOUND_INTERVIEW;
import static com.catchyou.domain.interview.exception.InterviewErrorCode.NOT_FOUND_SELECTED_INTERVIEW;

@Adaptor
@RequiredArgsConstructor
public class InterviewAdaptor {
    private final InterviewRepository interviewRepository;

    public Interview save(Interview interview){
        return interviewRepository.save(interview);
    }

    public Interview findById(Long id){
        return interviewRepository.findById(id)
                .orElseThrow(() -> new BaseException(NOT_FOUND_INTERVIEW));
    }

    public List<Interview> findByCriminal(Criminal criminal){
        return interviewRepository.findAllByCriminal(criminal);
    }

    public Interview findSelectedInterview(Criminal criminal){
        return interviewRepository.findByCriminalAndSelected(criminal, Status.Y)
                .orElseThrow(() -> new BaseException(NOT_FOUND_SELECTED_INTERVIEW));
    }
}
