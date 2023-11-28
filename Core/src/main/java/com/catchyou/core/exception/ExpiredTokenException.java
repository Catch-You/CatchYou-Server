package com.catchyou.core.exception;

public class ExpiredTokenException extends BaseException{
    public static final BaseException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(GlobalErrorCode.REFRESH_TOKEN_EXPIRED_ERROR);
    }
}
