package com.m0ncld.sso.webapp2.auth;

import javax.validation.constraints.NotNull;
import java.security.Principal;

interface AuthRepository {

    UserAuthData getUserAuthData(@NotNull Principal principal);
}
