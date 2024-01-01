package com.m0ncld.sso.webapp2.helloworld;

interface HelloWorldRepository {

    HelloWorldModel helloWorldAll(String name);
    HelloWorldModel helloWorldGuest(String name);
    HelloWorldModel helloWorldUser(String name);
}
