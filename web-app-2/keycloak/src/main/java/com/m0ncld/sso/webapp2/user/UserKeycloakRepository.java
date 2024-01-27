package com.m0ncld.sso.webapp2.user;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserKeycloakRepository implements UserRepository {
    @Override
    public Optional<UserModel> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public Collection<UserModel> findAll() {
        return null;
    }

    @Override
    public Collection<UserModel> findAllById(Collection<UUID> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
