package com.m0ncld.sso.webapp2.auth;

import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class AuthController {

    private final AuthRepository repository;

    public AuthController(AuthRepository repository) {
        this.repository = repository;
    }

    public UserAuthData getUserAuthData(Principal principal) {
        if (principal == null) {
            return UserAuthData.builder().build();
        }
        return repository.getUserAuthData(principal);
    }
}
