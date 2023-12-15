package com.catchyou.api.interview.controller;

import com.catchyou.api.interview.dto.InterviewMontageListResponse;
import com.catchyou.api.interview.service.InterviewService;
import com.catchyou.core.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview")
public class InterviewController {
    private final InterviewService interviewService;

    @PostMapping("/{criminalId}")
    public BaseResponse<Long> createInterview(@PathVariable Long criminalId){
        return interviewService.createInterview(criminalId);
    }

    @GetMapping("/montages/{interviewId}")
    public InterviewMontageListResponse getInterviewMontageList(@PathVariable Long interviewId){
        return interviewService.getInterviewMontageList(interviewId);
    }
}
