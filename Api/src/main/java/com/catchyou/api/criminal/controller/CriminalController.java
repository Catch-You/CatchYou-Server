package com.catchyou.api.criminal.controller;

import com.catchyou.api.criminal.dto.CreateCriminalRequest;
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