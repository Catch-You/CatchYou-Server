package com.catchyou.core.exception;

import com.catchyou.core.dto.ErrorDetail;

public interface BaseErrorCode {
    ErrorDetail getErrorDetail();

    String getExplainError() throws NoSuchFieldException;
}
