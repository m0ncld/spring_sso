package com.m0ncld.sso.webapp2.todolist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.m0ncld.sso.webapp2.util.GenericModel;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

/**
 * Model of item in todo list
 */
@JsonDeserialize(builder = TodoListItemModel.Builder.class)
public class TodoListItemModel extends GenericModel<UUID> {

    /**
     * Todo list item text
     */
    @NotBlank
    private final String text;

    /**
     * Flag if the item was checked
     */
    private final boolean checked;

    private TodoListItemModel(Builder builder) {
        super(builder.id);
        this.text = builder.text;
        this.checked = builder.checked;
    }

    /**
     * Returns the todo list item text
     * @return Todo list item text
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the flag if the item is checked in the todo list
     * @return True, if the item is checked or false if not
     */
    public boolean isChecked() {
        return checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoListItemModel that)) return false;
        if (!super.equals(o)) return false;
        return checked == that.checked && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text, checked);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder builderOf() {
        return new Builder(this);
    }

    @Override
    public String toString() {
        return "TodoListItem{" +
                "text='" + text + '\'' +
                ", checked=" + checked +
                ", id=" + id +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {

        private UUID id;
        private String text;
        private boolean checked;

        Builder() {}

        private Builder(TodoListItemModel source) {
            this.id = source.id;
            this.text = source.text;
            this.checked = source.checked;
        }

        /**
         * Sets the todo list item identifier in builder
         * @param id Todo list item identifier
         * @return Builder
         */
        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the todo list item text in builder
         * @param text Todo list item text
         * @return Builder
         */
        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets the todo list item checked flag in builder
         * @param checked Todo list item checked flag
         * @return Builder
         */
        public Builder withChecked(boolean checked) {
            this.checked = checked;
            return this;
        }

        /**
         * Build the todo list item model
         * @return Todo list item model
         */
        public TodoListItemModel build() {
            return new TodoListItemModel(this);
        }
    }
}
