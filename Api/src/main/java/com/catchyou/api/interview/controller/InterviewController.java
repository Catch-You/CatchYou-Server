package com.catchyou.api.interview.controller;

import com.catchyou.api.interview.service.InterviewService;
import com.catchyou.core.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview")
public class InterviewController {
    private final InterviewService interviewService;

    @PostMapping("/{criminalId}")
    public BaseResponse<Long> createInterview(@PathVariable Long criminalId){
        return interviewService.createInterview(criminalId);
    }
}
