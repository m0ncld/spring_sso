package com.m0ncld.sso.webapp2.helloworld;

import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicInteger;

@Repository
class HelloWorldRepositoryMyslq implements HelloWorldRepository {

    private final AtomicInteger versionCounter = new AtomicInteger(1);

    @Override
    public HelloWorldModel helloWorldAll(String name) {
        return HelloWorldModel.builder()
                .withName(name)
                .withMessage("Hello World All!")
                .withVersion(versionCounter.incrementAndGet())
                .build();
    }

    @Override
    public HelloWorldModel helloWorldGuest(String name) {
        return HelloWorldModel.builder()
                .withName(name)
                .withMessage("Hello World Guest!")
                .withVersion(versionCounter.incrementAndGet())
                .build();
    }

    @Override
    public HelloWorldModel helloWorldUser(String name) {
        return HelloWorldModel.builder()
                .withName(name)
                .withMessage("Hello World User!")
                .withVersion(versionCounter.incrementAndGet())
                .build();
    }
}
