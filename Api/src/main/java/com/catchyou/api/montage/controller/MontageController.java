package com.catchyou.api.montage.controller;

import com.catchyou.api.montage.dto.CreateMontageRequest;
import com.catchyou.api.montage.dto.MontageResponse;
import com.catchyou.api.montage.service.MontageService;
import com.catchyou.core.dto.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/montage")
public class MontageController {
    private final MontageService montageService;

    @PostMapping("/create/{interviewId}")
    public MontageResponse createMontage(@PathVariable Long interviewId,
                                         @RequestBody @Valid CreateMontageRequest request){
        return montageService.createMontage(interviewId, request);
    }
}
