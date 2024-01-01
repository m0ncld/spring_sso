package com.m0ncld.sso.webapp2.helloworld;

import java.io.Serializable;
import java.util.Objects;

public class HelloWorldModel implements Serializable {

    private final String name;
    private final String message;
    private final int version;

    private HelloWorldModel(Builder builder) {
        this.name = builder.name;
        this.message = builder.message;
        this.version = builder.version;
    }

    public String getName() {
        return name;
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
        return version == that.version && Objects.equals(name, that.name) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, message, version);
    }

    @Override
    public String toString() {
        return "HelloWorldModel{" +
                "name='" + name + '\'' +
                "message='" + message + '\'' +
                ", version=" + version +
                '}';
    }

    public static class Builder {
        private String name;
        private String message;
        private int version;

        private Builder() {}

        private Builder(HelloWorldModel source) {
            this.name = source.getName();
            this.message = source.getMessage();
            this.version = source.getVersion();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
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
