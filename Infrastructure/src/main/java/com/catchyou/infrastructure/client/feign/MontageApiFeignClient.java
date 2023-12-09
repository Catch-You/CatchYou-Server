package com.catchyou.infrastructure.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "MontageApiFeignClient",
        url = "${client.montage.apiUrl}"
) public interface MontageApiFeignClient {

    @PostMapping("/api/generate_and_save")
    void callMontageApi(
            @RequestParam("prompt") String prompt,
            @RequestParam("file_name") String fileName
    );
}
