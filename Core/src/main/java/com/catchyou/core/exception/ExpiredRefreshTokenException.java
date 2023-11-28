package com.catchyou.core.exception;

public class ExpiredRefreshTokenException extends BaseException{
    public static final BaseException EXCEPTION = new ExpiredRefreshTokenException();

    private ExpiredRefreshTokenException() {
        super(GlobalErrorCode.REFRESH_TOKEN_EXPIRED_ERROR);
    }
}
