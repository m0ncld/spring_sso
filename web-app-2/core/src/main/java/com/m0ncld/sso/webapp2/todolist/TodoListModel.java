package com.m0ncld.sso.webapp2.todolist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.m0ncld.sso.webapp2.util.GenericModel;

import java.util.UUID;


@JsonDeserialize(builder = TodoListModel.Builder.class)
public class TodoListModel extends GenericModel<UUID> {

    private TodoListModel(Builder builder) {
        super(builder.id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder builderOf() {
        return new Builder(this);
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;

        Builder() {}

        private Builder(TodoListModel source) {
            this.id = source.id;
        }

        public UUID getId() {
            return id;
        }

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public TodoListModel build() {
            return new TodoListModel(this);
        }
    }
}
