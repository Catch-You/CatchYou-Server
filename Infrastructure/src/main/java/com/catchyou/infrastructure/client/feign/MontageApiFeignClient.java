package com.catchyou.infrastructure.client.feign;

import com.catchyou.infrastructure.config.feign.FeignClientConfig;
import com.catchyou.infrastructure.config.feign.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "MontageApiFeignClient",
        url = "${client.montage.apiUrl}",
        configuration = FeignClientConfig.class
) public interface MontageApiFeignClient {

    @PostMapping("/api/generate_and_save")
    void callMontageApi(
            @RequestParam("prompt") String prompt,
            @RequestParam("file_name") String fileName
    );
}
