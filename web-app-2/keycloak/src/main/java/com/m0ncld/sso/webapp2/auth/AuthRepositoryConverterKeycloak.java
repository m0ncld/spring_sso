package com.m0ncld.sso.webapp2.auth;

import com.m0ncld.sso.webapp2.util.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthRepositoryConverterKeycloak
        implements Converter<OAuth2AuthenticationToken, UserAuthData> {

    private final AuthAttributeMapping mapping;

    public AuthRepositoryConverterKeycloak(AuthAttributeMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public OAuth2AuthenticationToken convertTo(UserAuthData entity) {
        throw new UnsupportedOperationException("Converting UserAuthData to OAuth2AuthenticationToken is not supported");
    }

    @Override
    public UserAuthData convertFrom(OAuth2AuthenticationToken model) {
        return UserAuthData.builder()
                .withId(mapping.getId(model))
                .withFirstName(mapping.getFirstName(model))
                .withLastName(mapping.getLastName(model))
                .withUserName(mapping.getUserName(model))
                .withAuthenticated(model.isAuthenticated())
                .withRoles(model.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
    }
}
