package com.m0ncld.sso.webapp2.auth;

import com.m0ncld.sso.webapp2.util.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/rest/auth")
class AuthResourceV1 {

    private final AuthController authController;

    public AuthResourceV1(AuthController authController) {
        this.authController = authController;
    }

    @GetMapping
    ResponseEntity<UserAuthData> getUserData(Principal principal) {
        return ApiUtil.of(authController.getUserAuthData(principal));
    }
}
