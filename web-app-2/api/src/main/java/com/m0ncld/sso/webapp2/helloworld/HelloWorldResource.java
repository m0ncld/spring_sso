package com.m0ncld.sso.webapp2.helloworld;

import com.m0ncld.sso.webapp2.config.AuthoritiesConstants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/rest/helloworld")
class HelloWorldResource {

    private final HelloWorldService service;

    HelloWorldResource(HelloWorldService service) {
        this.service = service;
    }

    @GetMapping("/all")
    protected HelloWorldModel helloWorldAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return service.helloWorldAll(authentication.getPrincipal().toString());
    }

    @GetMapping("/guest")
    @Secured(AuthoritiesConstants.GUEST)
    protected HelloWorldModel helloWorldGuest(Principal principal) {

        return service.helloWorldGuest(principal.getName());
    }

    @GetMapping("/user")
    @Secured(AuthoritiesConstants.USER)
    protected HelloWorldModel helloWorldUser(Principal principal) {

        return service.helloWorldUser(principal.getName());
    }
}
