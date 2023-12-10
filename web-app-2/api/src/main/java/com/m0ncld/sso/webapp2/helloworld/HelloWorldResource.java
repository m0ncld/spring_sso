package com.m0ncld.sso.webapp2.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/helloworld")
class HelloWorldResource {

    private final HelloWorldService service;

    HelloWorldResource(HelloWorldService service) {
        this.service = service;
    }

    @GetMapping
    protected HelloWorldModel helloWorld() {
        return service.helloWorld();
    }
}
