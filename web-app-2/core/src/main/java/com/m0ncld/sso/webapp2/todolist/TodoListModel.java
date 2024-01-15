package com.m0ncld.sso.webapp2.todolist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.m0ncld.sso.webapp2.util.GenericModel;
import com.m0ncld.sso.webapp2.util.SerializationUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Model of the todo list
 */
@JsonDeserialize(builder = TodoListModel.Builder.class)
public class TodoListModel extends GenericModel<UUID> {

    /**
     * Todo list name
     */
    @NotBlank
    private final String name;

    /**
     * Todo list description
     */
    private final String description;

    /**
     * Todo list user identifier
     */
    @NotNull
    private final UUID userId;

    /**
     * Todo list user username
     */
    @NotBlank
    private final String username;

    /**
     * Todo list image in base64 format
     */
    private final String base64Image;

    /**
     * Todo list creating date
     */
    @NotNull
    private final ZonedDateTime createdAt;

    /**
     * Todo list modification date
     */
    @NotNull
    private final ZonedDateTime modifiedAt;

    /**
     * Todo list items
     */
    @NotNull
    @Valid
    private final List<TodoListItemModel> items;

    private TodoListModel(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.description = builder.description;
        this.userId = builder.userId;
        this.username = builder.username;
        this.base64Image = builder.base64Image;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
        this.items = SerializationUtil.unmodifable(builder.items);
    }

    /**
     * Returns the name of the todo list
     * @return Todo list name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the todo list
     * @return Todo list description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the identifier of user that created the todo list
     * @return Todo list user identifier
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Returns the username of the user that created the todo list
     * @return Todo list username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the image in base64 format of the todo list
     * @return Todo list image in base64 format
     */
    public String getBase64Image() {
        return base64Image;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the modification date of the todo list
     * @return Todo list modification date
     */
    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Return the items of the todo list
     * @return Todo list items
     */
    public List<TodoListItemModel> getItems() {
        return items;
    }

    /**
     * Create new builder of todo list
     * @return Todo list builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Create new builder of todo list filled with current object
     * @return Todo list builder
     */
    public Builder builderOf() {
        return new Builder(this);
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;
        private String name;
        private String description;

        private UUID userId;
        private String username;
        private String base64Image;
        private ZonedDateTime createdAt;
        private ZonedDateTime modifiedAt;

        private List<TodoListItemModel> items = new ArrayList<>();

        Builder() {}

        private Builder(TodoListModel source) {
            this.id = source.id;
            this.name = source.name;
            this.description = source.description;
            this.userId = source.userId;
            this.username = source.username;
            this.base64Image = source.base64Image;
            this.createdAt = source.createdAt;
            this.modifiedAt = source.modifiedAt;
            this.items = SerializationUtil.modifable(source.items);
        }

        /**
         * Sets the identifier in the todo list builder
         * @param id Todo list identifier
         * @return Builder
         */
        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the name in the todo list builder
         * @param name Todo list name
         * @return Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the description in the todo list builder
         * @param description Todo list description
         * @return Builder
         */
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the user identifier in the todo list builder
         * @param userId Todo list user identifier
         * @return Builder
         */
        public Builder withUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Sets the username in the todo list builder
         * @param username Todo list username
         * @return Builder
         */
        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the image in base64 format in the todo list builder
         * @param base64Image Todo list image in base64 format
         * @return Builder
         */
        public Builder withBase64Image(String base64Image) {
            this.base64Image = base64Image;
            return this;
        }

        /**
         * Sets the creation date in the todo list builder
         * @param createdAt Creation date
         * @return Builder
         */
        public Builder withCreatedAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the modification date in the todo list builder
         * @param modifiedAt Todo list modification date
         * @return Builder
         */
        public Builder withModifiedAt(ZonedDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        /**
         * Sets the items in the todo list builder
         * @param items Todo list items
         * @return Builder
         */
        public Builder withItems(List<TodoListItemModel> items) {
            this.items = items;
            return this;
        }

        /**
         * Adds the item to the todo list builder
         * @param item Todo list item
         * @return Builder
         */
        public Builder addItem(TodoListItemModel item) {
            this.items.add(item);
            return this;
        }

        /**
         * Creates the todo list
         * @return Todo list
         */
        public TodoListModel build() {
            return new TodoListModel(this);
        }
    }
}
