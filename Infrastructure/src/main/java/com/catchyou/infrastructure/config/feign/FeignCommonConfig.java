package com.catchyou.infrastructure.config.feign;

import com.catchyou.core.exception.BaseErrorCode;
import com.catchyou.core.exception.BaseException;
import feign.FeignException;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

import static com.catchyou.core.exception.GlobalErrorCode.CALL_MONTAGE_API_FAILED;

@Configuration
public class FeignCommonConfig{

    @Bean
    public ErrorDecoder errorDecoder() {
        return ((methodKey, response) -> {
            if(response.status() != 200) {
                return new BaseException(CALL_MONTAGE_API_FAILED);
            }
            return null;
        });
    }
}
