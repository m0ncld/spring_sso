package com.m0ncld.sso.webapp2.auth;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.Optional;

@Repository
public class AuthRepositoryKeycloak implements AuthRepository {

    private final AuthRepositoryConverterKeycloak converter;

    public AuthRepositoryKeycloak(AuthRepositoryConverterKeycloak converter) {
        this.converter = converter;
    }

    @Override
    public UserAuthData getUserAuthData(Principal principal) {
        return Optional.of(principal)
                .filter(OAuth2AuthenticationToken.class::isInstance)
                .map(OAuth2AuthenticationToken.class::cast)
                .map(converter::convertFrom)
                .orElseThrow(() -> new IllegalArgumentException("Principal is not a OAuth2AuthenticationToken instance"));
    }
}
