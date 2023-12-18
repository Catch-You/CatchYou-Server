package com.catchyou.api.montage.service;

import com.catchyou.api.config.security.UserUtils;
import com.catchyou.api.montage.dto.CreateMontageRequest;
import com.catchyou.api.montage.dto.MontageResponse;
import com.catchyou.core.dto.BaseResponse;
import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.adaptor.CriminalAdaptor;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.validator.CriminalValidator;
import com.catchyou.domain.interview.adaptor.InterviewAdaptor;
import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.interview.validator.InterviewValidator;
import com.catchyou.domain.montage.adaptor.MontageAdaptor;
import com.catchyou.domain.montage.entity.Montage;
import com.catchyou.domain.montage.repository.MontageRepository;
import com.catchyou.domain.user.adaptor.UserAdaptor;
import com.catchyou.domain.user.entity.User;
import com.catchyou.infrastructure.client.feign.MontageApiFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MontageService {
    private final UserUtils userHelper;
    private final MontageAdaptor montageAdaptor;
    private final InterviewAdaptor interviewAdaptor;
    private final InterviewValidator interviewValidator;
    private final CriminalValidator criminalValidator;
    private final MontageApiFeignClient montageApiFeignClient;

    public MontageResponse createMontage(Long interviewId, CreateMontageRequest request){
        User currentUser = userHelper.getCurrentUser();

        Interview interview = interviewAdaptor.findById(interviewId);

        //인터뷰 할 수 있는 몽타주 제작자인지(사건 부여받은 제작자인지) 확인
        interviewValidator.isValidInterviewDirector(interviewId, currentUser);

        //이미 사건에 대해 확정된 몽타주 있는지 확인
        criminalValidator.isValidCreateMontage(interview.getCriminal());

        //인터뷰에서 확정된 몽타주가 있는지 확인
        interviewValidator.isValidCreateInterviewMontage(interview);

        Montage montage = request.toEntity(interview);

        montageAdaptor.save(montage);

        //api 호출
        montageApiFeignClient.callMontageApi(request.getPrompt(), montage.getId().toString());
        montageAdaptor.save(montage);

        return MontageResponse.of(request.getPrompt(), montage);
    }

}
