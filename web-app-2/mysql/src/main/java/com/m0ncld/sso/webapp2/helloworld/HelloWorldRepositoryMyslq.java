package com.m0ncld.sso.webapp2.helloworld;

import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicInteger;

@Repository
class HelloWorldRepositoryMyslq implements HelloWorldRepository {

    private final AtomicInteger versionCounter = new AtomicInteger(1);

    @Override
    public HelloWorldModel helloWorld() {
        return HelloWorldModel.builder()
                .withMessage("Hello World!")
                .withVersion(versionCounter.incrementAndGet())
                .build();
    }
}
