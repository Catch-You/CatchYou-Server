package com.catchyou.api.interview.service;

import com.catchyou.api.config.security.UserUtils;
import com.catchyou.core.dto.BaseResponse;
import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.adaptor.CriminalAdaptor;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.validator.CriminalValidator;
import com.catchyou.domain.interview.adaptor.InterviewAdaptor;
import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.user.adaptor.UserAdaptor;
import com.catchyou.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InterviewService {
    private final UserUtils userHelper;
    private final UserAdaptor userAdaptor;
    private final CriminalAdaptor criminalAdaptor;
    private final CriminalValidator criminalValidator;
    private final InterviewAdaptor interviewAdaptor;

    public BaseResponse<Long> createInterview(Long criminalId){
        User currentUser = userHelper.getCurrentUser();
        Criminal criminal = criminalAdaptor.findById(criminalId);

        //해당 사건의 몽타주 제작자인지 확인
        criminalValidator.isValidCriminalDirector(criminal, currentUser);

        //이미 확정된 몽타주가 있는지 확인
        criminalValidator.isValidCreateMontage(criminal);

        Interview interview = interviewAdaptor.save(
                Interview.builder()
                .criminal(criminal)
                .selectStatus(Status.N)
                .selected(Status.N)
                .build()
        );
        return BaseResponse.of("인터뷰 생성되었습니다.", interview.getId());
    }
}
