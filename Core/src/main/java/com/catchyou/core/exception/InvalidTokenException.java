package com.catchyou.core.exception;

public class InvalidTokenException extends BaseException{
    public static final BaseException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(GlobalErrorCode.INVALID_TOKEN_ERROR);
    }
}
