package com.catchyou.infrastructure.config.feign;

import com.catchyou.core.exception.BaseException;
import feign.Response;
import feign.codec.ErrorDecoder;

import static com.catchyou.core.exception.GlobalErrorCode.CALL_MONTAGE_API_FAILED;

public class FeignErrorDecoder implements ErrorDecoder{
    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() != 200)
            return new BaseException(CALL_MONTAGE_API_FAILED);

        return null;
    }
}
