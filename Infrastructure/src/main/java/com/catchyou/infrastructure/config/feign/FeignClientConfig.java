package com.catchyou.infrastructure.config.feign;

import com.catchyou.infrastructure.client.feign.BaseFeignClientPackage;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = BaseFeignClientPackage.class)
public class FeignClientConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignErrorDecoder();
    }
}
