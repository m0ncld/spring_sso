package com.m0ncld.sso.webapp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.m0ncld.sso")
@EnableScheduling
public class WebApp2Application {

    public static void main(String[] args) {
        SpringApplication.run(WebApp2Application.class, args);
    }
}
