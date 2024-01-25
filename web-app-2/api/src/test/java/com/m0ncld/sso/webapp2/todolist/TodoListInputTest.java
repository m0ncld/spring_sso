package com.m0ncld.sso.webapp2.todolist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TodoListInputTest {

    private static final UUID ID = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");

    @Test
    void emptyBuilder() {
        var result = TodoListInput.builder().build();
        Assertions.assertNull(result.getId());
        Assertions.assertNull(result.getName());
        Assertions.assertNull(result.getDescription());
        Assertions.assertNull(result.getBase64Image());
        Assertions.assertNotNull(result.getItems());
    }

    @Test
    void fullBuilder() {
        var name = "name";
        var description = "description";
        var base64Image = "base64Image";
        var items = List.of(TodoListItemModel.builder().build());
        var result = TodoListInput.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withBase64Image(base64Image)
                .withItems(items)
                .build();
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(description, result.getDescription());
        Assertions.assertEquals(base64Image, result.getBase64Image());
        Assertions.assertEquals(items, result.getItems());
    }

    @Test
    void builderOf() {
        var name = "name";
        var description = "description";
        var base64Image = "base64Image";
        var items = List.of(TodoListItemModel.builder().build());
        var model1 = TodoListInput.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withBase64Image(base64Image)
                .withItems(items)
                .build();
        var model2 = model1.builderOf().build();
        Assertions.assertNotSame(model1, model2);
        Assertions.assertEquals(model1, model2);
    }

    @Test
    void immutableList() {
        var name = "name";
        var description = "description";
        var base64Image = "base64Image";
        var items = new ArrayList<TodoListItemModel>();
        items.add(TodoListItemModel.builder().build());
        var model = TodoListInput.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withBase64Image(base64Image)
                .withItems(items)
                .build();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> model.getItems().add(TodoListItemModel.builder().build()));
    }

    @Test
    void toModelBuilder() {
        var name = "name";
        var description = "description";
        var base64Image = "base64Image";
        var items = new ArrayList<TodoListItemModel>();
        items.add(TodoListItemModel.builder().build());
        var model = TodoListInput.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withBase64Image(base64Image)
                .withItems(items)
                .build();
        var result = model.toModelBuilder().build();
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(description, result.getDescription());
        Assertions.assertEquals(base64Image, result.getBase64Image());
        Assertions.assertEquals(items, result.getItems());
    }
}