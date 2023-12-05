package com.catchyou.api.criminal.controller;

import com.catchyou.api.criminal.dto.CreateCriminalRequest;
import com.catchyou.api.criminal.dto.MyCriminalDetailsDto;
import com.catchyou.api.criminal.dto.MyCriminalListResponse;
import com.catchyou.api.criminal.dto.UpdateCriminalRequest;
import com.catchyou.api.criminal.service.CriminalService;
import com.catchyou.api.signup.dto.SignupRequest;
import com.catchyou.core.dto.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/criminal")
public class CriminalController {
    private final CriminalService criminalService;

    //경찰이 자신이 등록한 사건만 상세 조회
    @GetMapping("/{criminalId}")
    public MyCriminalDetailsDto getCriminalDetails(@PathVariable Long criminalId) {
        return criminalService.getCriminalDetails(criminalId);
    }

    //경찰이 자신이 등록한 사건만 목록 조회
    @GetMapping("/myList")
    public MyCriminalListResponse getCriminalList(){
        return criminalService.getCriminalList();
    }

    @PostMapping
    public BaseResponse<Long> createCriminal(@RequestBody @Valid CreateCriminalRequest request) {
        return criminalService.createCriminal(request);
    }

    @PutMapping("/{criminalId}")
    public BaseResponse<Long> updateCriminal(@PathVariable Long criminalId,
                                             @RequestBody @Valid UpdateCriminalRequest request){
        return criminalService.updateCriminal(criminalId, request);
    }
}
