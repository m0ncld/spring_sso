package com.m0ncld.sso.webapp2.user;

import com.m0ncld.sso.webapp2.util.GenericModel;

import java.util.UUID;

public class UserModel extends GenericModel<UUID> {
    protected UserModel(UUID id) {
        super(id);
    }
}
