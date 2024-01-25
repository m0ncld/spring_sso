package com.m0ncld.sso.webapp2.config;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    /**
     * User role
     */
    public static final String USER = "ROLE_USER";

    /**
     * Administration role
     */
    public static final String ADMIN = "ROLE_ADMIN";

    /**
     * Guest role
     */
    public static final String GUEST = "ROLE_GUEST";

    /**
     * Anonymous role
     */
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {}
}
