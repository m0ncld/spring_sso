package com.m0ncld.sso.webapp2.user;

import com.m0ncld.sso.webapp2.util.GenericReadRepository;

import java.util.UUID;

interface UserRepository extends GenericReadRepository<UserModel, UUID> {
}
