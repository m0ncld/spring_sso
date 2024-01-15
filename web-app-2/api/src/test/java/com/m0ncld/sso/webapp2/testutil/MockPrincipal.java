package com.m0ncld.sso.webapp2.testutil;

import javax.security.auth.Subject;
import java.security.Principal;

public class MockPrincipal implements Principal {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
