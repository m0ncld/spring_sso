package com.m0ncld.sso.webapp2.auth;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.m0ncld.sso.webapp2.user.UserData;
import com.m0ncld.sso.webapp2.util.SerializationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(builder = UserAuthData.Builder.class)
public class UserAuthData extends UserData {

    private final List<String> roles;
    private final boolean authenticated;

    UserAuthData(Builder builder) {
        super(builder);
        this.roles = SerializationUtil.unmodifable(builder.roles);
        this.authenticated = builder.authenticated;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthData that)) return false;
        if (!super.equals(o)) return false;
        return authenticated == that.authenticated
                && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roles, authenticated);
    }

    @Override
    public String toString() {
        return "UserAuthData{" +
                "roles=" + roles +
                ", authenticated=" + authenticated +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public Builder builderOf() {
        return new Builder(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder extends  UserData.Builder {

        private List<String> roles = new ArrayList<>();
        private boolean authenticated;

        Builder() {
            super();
        }

        protected Builder(UserAuthData authData) {
            super(authData);
            this.roles = SerializationUtil.modifable(authData.roles);
            this.authenticated = authData.authenticated;
        }

        @Override
        public Builder withId(UUID identifier) {
            super.withId(identifier);
            return this;
        }

        @Override
        public Builder withUserName(String userName) {
            super.withUserName(userName);
            return this;
        }

        @Override
        public Builder withFirstName(String firstName) {
            super.withFirstName(firstName);
            return this;
        }

        @Override
        public Builder withLastName(String lastName) {
            super.withLastName(lastName);
            return this;
        }

        @Override
        public Builder withEmail(String email) {
            super.withEmail(email);
            return this;
        }

        public Builder withRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public Builder withAuthenticated(boolean authenticated) {
            this.authenticated = authenticated;
            return this;
        }

        @Override
        public UserAuthData build() {
            return new UserAuthData(this);
        }
    }

}
