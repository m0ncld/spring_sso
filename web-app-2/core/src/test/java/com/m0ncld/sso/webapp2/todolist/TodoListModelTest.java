package com.m0ncld.sso.webapp2.todolist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

class TodoListModelTest {

    private final UUID ID = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");

    private final UUID USER_ID = UUID.fromString("285ffa74-5174-8921-8cb6-72ac0f176aff");

    @Test
    void emptyBuilder() {
        var result = TodoListModel.builder().build();
        Assertions.assertNull(result.getName());
        Assertions.assertNull(result.getDescription());
        Assertions.assertNull(result.getUserId());
        Assertions.assertNull(result.getUsername());
        Assertions.assertNull(result.getBase64Image());
        Assertions.assertNull(result.getCreatedAt());
        Assertions.assertNull(result.getModifiedAt());
        Assertions.assertNotNull(result.getItems());
        Assertions.assertTrue(result.getItems().isEmpty());
    }

    @Test
    void fullBuilder() {
        var name = "name";
        var description = "description";
        var username = "username";
        var base64Image = "base64Image";
        var createdAt = ZonedDateTime.of(1990, 4, 26, 3, 4, 5, 6, ZoneId.systemDefault());
        var modifiedAt = ZonedDateTime.of(2023, 5, 26, 6, 7, 8, 9, ZoneId.systemDefault());

        var items = List.of(TodoListItemModel.builder().build());

        var result = TodoListModel.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withUserId(USER_ID)
                .withUsername(username)
                .withBase64Image(base64Image)
                .withCreatedAt(createdAt)
                .withModifiedAt(modifiedAt)
                .withItems(items)
                .build();

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(description, result.getDescription());
        Assertions.assertEquals(USER_ID, result.getUserId());
        Assertions.assertEquals(username, result.getUsername());
        Assertions.assertEquals(base64Image, result.getBase64Image());
        Assertions.assertEquals(createdAt, result.getCreatedAt());
        Assertions.assertEquals(modifiedAt, result.getModifiedAt());
        Assertions.assertEquals(items, result.getItems());
    }

    @Test
    void builderOf() {
        var name = "name";
        var description = "description";
        var username = "username";
        var base64Image = "base64Image";
        var createdAt = ZonedDateTime.of(1990, 4, 26, 3, 4, 5, 6, ZoneId.systemDefault());
        var modifiedAt = ZonedDateTime.of(2023, 5, 26, 6, 7, 8, 9, ZoneId.systemDefault());

        var items = List.of(TodoListItemModel.builder().build());

        var model = TodoListModel.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withUserId(USER_ID)
                .withUsername(username)
                .withBase64Image(base64Image)
                .withCreatedAt(createdAt)
                .withModifiedAt(modifiedAt)
                .withItems(items)
                .build();
        var result = model.builderOf().build();

        Assertions.assertEquals(model, result);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(description, result.getDescription());
        Assertions.assertEquals(USER_ID, result.getUserId());
        Assertions.assertEquals(username, result.getUsername());
        Assertions.assertEquals(base64Image, result.getBase64Image());
        Assertions.assertEquals(createdAt, result.getCreatedAt());
        Assertions.assertEquals(modifiedAt, result.getModifiedAt());
        Assertions.assertEquals(items, result.getItems());
    }

    @Test
    void immutableMap() {
        var item = TodoListItemModel.builder().build();
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> TodoListModel.builder().build().getItems().add(item));
    }

    @Test
    void addItem() {
        var item = TodoListItemModel.builder().build();
        var result = TodoListModel.builder()
                .addItem(item)
                .build();
        Assertions.assertEquals(1, result.getItems().size());
        Assertions.assertEquals(item, result.getItems().get(0));
    }
}