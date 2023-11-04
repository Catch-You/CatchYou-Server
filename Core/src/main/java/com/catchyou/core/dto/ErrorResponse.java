package com.catchyou.core.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final boolean success = false;
    private final Integer statusCode;
    private final String errorCode;
    private final String reason;
    private final LocalDateTime time;

    private final String path;

    public ErrorResponse(ErrorDetail errorDetail, String path) {
        this.statusCode = errorDetail.getStatusCode();
        this.errorCode = errorDetail.getErrorCode();
        this.reason = errorDetail.getReason();
        this.time = LocalDateTime.now();
        this.path = path;
    }

    public ErrorResponse(int statusCode, String errorCode,
                         String reason, String path) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.reason = reason;
        this.time = LocalDateTime.now();
        this.path = path;
    }
}
