package com.m0ncld.sso.webapp2.helloworld;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    private final HelloWorldRepository repository;

    public HelloWorldService(HelloWorldRepository repository) {
        this.repository = repository;
    }

    public HelloWorldModel helloWorld() {
        return repository.helloWorld();
    }
}
