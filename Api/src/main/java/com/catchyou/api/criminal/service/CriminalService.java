package com.catchyou.api.criminal.service;

import com.catchyou.api.criminal.dto.*;
import com.catchyou.api.config.security.UserUtils;
import com.catchyou.core.dto.BaseResponse;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.adaptor.CriminalAdaptor;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.exception.CriminalErrorCode;
import com.catchyou.domain.criminal.repository.CriminalRepository;
import com.catchyou.domain.criminal.validator.CriminalValidator;
import com.catchyou.domain.user.adaptor.UserAdaptor;
import com.catchyou.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.catchyou.core.consts.CatchyouStatic.CATCHYOU_CODE;
import static com.catchyou.domain.criminal.exception.CriminalErrorCode.CANNOT_REGISTER_TO_CRIMINAL;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CriminalService {
    private final CriminalAdaptor criminalAdaptor;
    private final CriminalValidator criminalValidator;
    private final UserAdaptor userAdaptor;
    private final UserUtils userHelper;

    //경찰일 때, 자신이 작성한 사건만 상세 조회
    public MyCriminalDetailsDto getCriminalDetails(Long criminalId) {
        User currentUser = userHelper.getCurrentUser();

        Criminal criminal = criminalAdaptor.findById(criminalId);
        criminalValidator.isValidCriminalUser(criminal, currentUser);   //작성자인지 확인

        return MyCriminalDetailsDto.of(criminal);
    }

    //경찰일 때, 자신이 작성한 사건만 목록 조회
    public MyCriminalListResponse getCriminalList(){
        User currentuser = userHelper.getCurrentUser();

        List<MyCriminalListDto> criminalListDtoList = criminalAdaptor.findByUser(currentuser).stream()
                .map(criminal -> MyCriminalListDto.of(criminal))
                .collect(Collectors.toList());

        return MyCriminalListResponse.from(criminalListDtoList);
    }


    public BaseResponse<Long> createCriminal(CreateCriminalRequest request) {
        User currentUser = userHelper.getCurrentUser();

        Criminal criminal = criminalAdaptor
                        .save(
                    request.toEntity(currentUser)
                        );

        String criminalCode = createCriminalCode(criminal.getId());
        criminal.updateCriminalCode(criminalCode);
        criminalAdaptor.save(criminal);
        return BaseResponse.of("사건 등록되었습니다.", criminal.getId());
    }

    public BaseResponse<Long> updateCriminal(Long criminalId, UpdateCriminalRequest request){
        User currentUser = userHelper.getCurrentUser();

        Criminal criminal = criminalAdaptor.findById(criminalId);

        //사건 작성자인지 확인
        criminalValidator.isValidCriminalUser(criminal, currentUser);

        //바꾸려는 상태값이 Y인지 확인
        checkStatusChangeable(request.getStatus(), criminal);

        criminal.updateCriminal(
                request.getTitle(), request.getSummary(), request.getDescription(),
                request.getRegion(), request.getCrimeType(), request.getStatus()
        );
        criminalAdaptor.save(criminal);
        return BaseResponse.of("사건 수정되었습니다.", criminal.getId());
    }

    //몽타주 제작자일 때, 사건 코드 검사를 통해 사건에 대한 접근 부여받음
    public BaseResponse<Long> confirmCriminalCode(String criminalCode) {
        User currentUser = userHelper.getCurrentUser();

        Criminal criminal = criminalAdaptor.findByCriminalCode(criminalCode);

        if(criminalValidator.alreadyExistDirector(criminal))
            throw new BaseException(CANNOT_REGISTER_TO_CRIMINAL);

        criminal.updateDirector(currentUser);
        criminalAdaptor.save(criminal);
        return BaseResponse.of("사건에 대한 접근이 처리되었습니다.", criminal.getId());
    }


    //확인 코드 만들기 : CATCHYOU-(랜덤코드4자리)-(사건아이디)
    private String createCriminalCode(Long criminalId){
        StringBuffer key = new StringBuffer();
        key.append(CATCHYOU_CODE);

        Random random = new Random();

        for(int i = 0; i<4; i++){
            int idx = random.nextInt(3);	//0~2 경우를 랜덤

            switch (idx){
                case 0:
                    key.append((char) ((int) random.nextInt(26) + 97));	//a~z
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));	//A~Z
                    break;
                case 2:
                    key.append(random.nextInt(10));	//0~9
                    break;
            }
        }

        key.append(criminalId);
        return key.toString();
    }

    private void checkStatusChangeable(Status status, Criminal criminal){
        if(status.equals(Status.Y)) {   //바꾸려는 상태 값이 공개면
            if(criminal.getSelectStatus().equals(Status.N)) //아직 몽타주 미정이면
                throw new BaseException(CriminalErrorCode.CANNOT_OPEN_TO_PUBLIC);
        }

    }

}
