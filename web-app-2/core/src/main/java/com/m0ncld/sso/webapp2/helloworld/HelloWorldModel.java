package com.m0ncld.sso.webapp2.helloworld;

import java.io.Serializable;
import java.util.Objects;

public class HelloWorldModel implements Serializable {

    private final String message;
    private final int version;

    private HelloWorldModel(Builder builder) {
        this.message = builder.message;
        this.version = builder.version;
    }

    public String getMessage() {
        return message;
    }

    public int getVersion() {
        return version;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder builderOf() {
        return new Builder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloWorldModel that = (HelloWorldModel) o;
        return version == that.version && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, version);
    }

    @Override
    public String toString() {
        return "HelloWorldModel{" +
                "message='" + message + '\'' +
                ", version=" + version +
                '}';
    }

    public static class Builder {
        private String message;
        private int version;

        private Builder() {}

        private Builder(HelloWorldModel source) {
            this.message = source.getMessage();
            this.version = source.getVersion();
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withVersion(int version) {
            this.version = version;
            return this;
        }

        public HelloWorldModel build() {
            return new HelloWorldModel(this);
        }
    }
}
