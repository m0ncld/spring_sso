package com.m0ncld.sso.webapp2.todolist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.m0ncld.sso.webapp2.util.GenericModel;
import com.m0ncld.sso.webapp2.util.SerializationUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Model of Todo list input
 *
 * Model is a resource input of Todo list but without a user specific,
 * modification date and creatin date
 */
@JsonDeserialize(builder = TodoListInput.Builder.class)
public class TodoListInput extends GenericModel<UUID> {

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
     * Todo list imager in base 64 format
     */
    private final String base64Image;

    /**
     * Items of the todo list
     */
    @Valid
    private final List<TodoListItemModel> items;

    private TodoListInput(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.description = builder.description;
        this.base64Image = builder.base64Image;
        this.items = SerializationUtil.unmodifable(builder.items);
    }

    /**
     * Returns todo list name
     * @return Todo list name
     */
    public String getName() {
        return name;
    }

    /**
     * Resturn todo list description
     * @return Todo list description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the todo list image in base 64 format
     * @return todo list image in base64 format
     */
    public String getBase64Image() {
        return base64Image;
    }

    /**
     * Returns the todo list items
     * @return Todo list items
     */
    public List<TodoListItemModel> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoListInput that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(base64Image, that.base64Image) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, base64Image, items);
    }

    @Override
    public String toString() {
        return "TodoListInput{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", base64Image='" + base64Image + '\'' +
                ", items=" + items +
                ", id=" + id +
                '}';
    }

    /**
     * Converts the Todo list input to the builder of todo list model
     * @return Todo list model builder with filled base fields
     */
    public TodoListModel.Builder toModelBuilder() {
        return TodoListModel.builder()
                .withId(getId())
                .withName(getName())
                .withDescription(getDescription())
                .withBase64Image(getBase64Image())
                .withItems(getItems());
    }

    /**
     * Todo list model input builder with copied all of fields
     * @return Todo list input builder
     */
    public Builder builderOf() {
        return new Builder(this);
    }

    /**
     * Returns new instance of todo list input builder
     * @return New instance of todo list input builder
     */
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private UUID id;
        private String name;
        private String description;
        private String base64Image;
        private List<TodoListItemModel> items = new ArrayList<>();

        Builder() {}

        private Builder(TodoListInput source) {
            this.id = source.id;
            this.name = source.name;
            this.description = source.description;
            this.base64Image = source.base64Image;
            this.items = SerializationUtil.modifable(source.items);
        }

        /**
         * Sets the todo list input identifier in builder
         * @param id Todo list input identifier
         * @return Builder
         */
        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the todo list input name in builder
         * @param name Todo list input name
         * @return Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the todo list input description in builder
         * @param description Todo list input description
         * @return Builder
         */
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the todo list input image in base64 format in builder
         * @param base64Image Todo list input image in base64 format
         * @return Builder
         */
        public Builder withBase64Image(String base64Image) {
            this.base64Image = base64Image;
            return this;
        }

        /**
         * Sets the todo list input items in builder
         * @param items Todo list input items
         * @return Builder
         */
        public Builder withItems(List<TodoListItemModel> items) {
            this.items = items;
            return this;
        }

        /**
         * Build the todo list input model
         * @return Todo list input model
         */
        public  TodoListInput build() {
            return new TodoListInput(this);
        }
    }
}
