package com.catchyou.core.exception;

import com.catchyou.core.dto.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private BaseErrorCode errorCode;

    public ErrorDetail getErrorDetail() {
        return this.errorCode.getErrorDetail();
    }
}
