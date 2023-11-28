package com.catchyou.api.signup.exception;

import com.catchyou.core.exception.BaseException;
import com.catchyou.core.exception.GlobalErrorCode;

public class CanNotSendMailException extends BaseException {
    public static final BaseException EXCEPTION = new CanNotSendMailException();

    private CanNotSendMailException() {
        super(GlobalErrorCode.MAIL_CAN_NOT_SEND_ERROR);
    }

}
