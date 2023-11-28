package com.catchyou.core.exception;

public class SecurityContextNotFoundException extends BaseException {

    public static final BaseException EXCEPTION = new SecurityContextNotFoundException();

    private SecurityContextNotFoundException() {
        super(GlobalErrorCode.SECURITY_CONTEXT_NOT_FOUND_ERROR);
    }
}