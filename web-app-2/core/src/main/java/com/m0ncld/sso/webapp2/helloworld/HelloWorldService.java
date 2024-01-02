package com.m0ncld.sso.webapp2.helloworld;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    private final HelloWorldRepository repository;

    public HelloWorldService(HelloWorldRepository repository) {
        this.repository = repository;
    }

    public HelloWorldModel helloWorldAll(String name) {
        return repository.helloWorldAll(name);
    }

    public HelloWorldModel helloWorldGuest(String name) {
        return repository.helloWorldGuest(name);
    }

    public HelloWorldModel helloWorldUser(String name) {
        return repository.helloWorldUser(name);
    }
}
