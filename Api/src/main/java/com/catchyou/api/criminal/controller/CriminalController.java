package com.catchyou.api.criminal.controller;

import com.catchyou.api.criminal.dto.*;
import com.catchyou.api.criminal.service.CriminalService;
import com.catchyou.api.interview.dto.SelectInterviewMontageRequest;
import com.catchyou.api.signup.dto.SignupRequest;
import com.catchyou.core.dto.BaseResponse;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/criminal")
public class CriminalController {
    private final CriminalService criminalService;

    //경찰이 자신이 등록한 사건만 상세 조회
    @GetMapping("/police/{criminalId}")
    public MyCriminalDetailsDto getCriminalDetails(@PathVariable Long criminalId) {
        return criminalService.getCriminalDetails(criminalId);
    }

    //경찰이 자신이 등록한 사건만 목록 조회
    @GetMapping("/police/myList")
    public MyCriminalListResponse getCriminalList(){
        return criminalService.getCriminalList();
    }

    @PostMapping("/police")
    public BaseResponse<Long> createCriminal(@RequestBody @Valid CreateCriminalRequest request) {
        return criminalService.createCriminal(request);
    }

    @PutMapping("/police/{criminalId}")
    public BaseResponse<Long> updateCriminal(@PathVariable Long criminalId,
                                             @RequestBody @Valid UpdateCriminalRequest request){
        return criminalService.updateCriminal(criminalId, request);
    }

    @PostMapping("/director/confirm-code/{criminalCode}")
    public BaseResponse<Long> confirmCode(@PathVariable String criminalCode){
        return criminalService.confirmCriminalCode(criminalCode);
    }

    //제작자가 권한부여받은 사건만 목록 조회
    @GetMapping("/director/myList")
    public DirectorCriminalListResponse getDirectorCriminalList(){
        return criminalService.getDirectorCriminalList();
    }

    //제작자가 권한부여받은 사건만 상세 조회
    @GetMapping("/director/{criminalId}")
    public DirectorCriminalDetailResponse getDirectorCriminalDetails(@PathVariable Long criminalId){

        return criminalService.getDirectorCriminalDetails(criminalId);
    }

    //제작자가 선택된 여러 몽타주들 중에 사건 몽타주 확정
    @PostMapping("/director/{criminalId}")
    public BaseResponse<Long> selectCriminalMontage(@PathVariable Long criminalId,
                                                    @RequestBody SelectInterviewMontageRequest request){
        return criminalService.selectCriminalMontage(criminalId, request);
    }

    //일반 유저(미로그인)들이 공개된 사건 리스트 조회
    @GetMapping("/open")
    public OpenCriminalListResponse getOpenCriminalList(@RequestParam(value="region") Region region){
        return criminalService.getOpenCriminalList(region);
    }

    //일반 유저들이 공개된 사건 상세 조회
    @GetMapping("/open/{criminalId}")
    public OpenCriminalDetailsDto getOpenCriminalDetails(@PathVariable Long criminalId){
        return criminalService.getOpenCriminalDetails(criminalId);
    }
}
