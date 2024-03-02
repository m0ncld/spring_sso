package com.m0ncld.sso.webapp2.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.m0ncld.sso.webapp2.util.GenericModel;

import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(builder = UserData.Builder.class)
public class UserData extends GenericModel<UUID> {
    protected final String userName;
    protected final String firstName;
    protected final String lastName;
    protected final String email;

    protected UserData(Builder builder) {
        super(builder.id);
        this.userName = builder.userName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData userData)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(userName, userData.userName)
                && Objects.equals(firstName, userData.firstName)
                && Objects.equals(lastName, userData.lastName)
                && Objects.equals(email, userData.email);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    public Builder builderOf() {
        return new Builder(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, firstName, lastName, email);
    }

    @JsonPOJOBuilder
    public static class Builder {

        private UUID id;
        private String userName;
        private String firstName;
        private String lastName;
        private String email;

        protected Builder() {}

        protected Builder(UserData source) {
            this.id = source.id;
            this.userName = source.userName;
            this.firstName = source.firstName;
            this.lastName = source.lastName;
            this.email = source.email;
        }

        public Builder withId(UUID identifier) {
            this.id = identifier;
            return this;
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserData build() {
            return new UserData(this);
        }
    }
}
