package com.catchyou;

import com.catchyou.infrastructure.client.feign.BaseFeignClientPackage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.util.Arrays;


@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "com.catchyou")
@Slf4j
public class CatchyouApiApplication implements ApplicationListener<ApplicationReadyEvent> {
    private final Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(CatchyouApiApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("applicationReady status" + Arrays.toString(environment.getActiveProfiles()));
    }
}