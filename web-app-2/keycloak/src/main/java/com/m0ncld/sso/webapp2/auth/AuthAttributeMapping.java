package com.m0ncld.sso.webapp2.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Optional;
import java.util.UUID;

@Configuration
@ConfigurationProperties("attribute-mapping")
public class AuthAttributeMapping {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;

    void setId(String id) {
        this.id = id;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getId(OAuth2AuthenticationToken model) {
        return getAttributeAsUUID(model, id);
    }

    public String getFirstName(OAuth2AuthenticationToken model) {
        return getAttribute(model, firstName);
    }

    public String getLastName(OAuth2AuthenticationToken model) {
        return getAttribute(model, lastName);
    }

    public String getUserName(OAuth2AuthenticationToken model) {
        return getAttribute(model, userName);
    }

    private String getAttribute(OAuth2AuthenticationToken model, String attributeName) {
        return Optional.ofNullable(model)
                .map(OAuth2AuthenticationToken::getPrincipal)
                .map(principal -> principal.getAttribute(attributeName))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .orElse(null);
    }

    private UUID getAttributeAsUUID(OAuth2AuthenticationToken model, String attributeName) {
        return Optional.ofNullable(model)
                .map(OAuth2AuthenticationToken::getPrincipal)
                .map(principal -> principal.getAttribute(attributeName))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(UUID::fromString)
                .orElse(null);
    }
}
