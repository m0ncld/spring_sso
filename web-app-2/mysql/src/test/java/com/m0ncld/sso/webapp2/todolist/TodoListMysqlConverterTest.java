package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.SerializationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TodoListMysqlConverterTest {

    private final UUID ID = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");

    private final UUID USER_ID = UUID.fromString("285ffa74-5174-8921-8cb6-72ac0f176aff");

    private TodoListMysqlConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TodoListMysqlConverter();
    }

    @Test
    void convertToEmpty() {
        var entity = new TodoListMysqlEntity();
        var result = converter.convertTo(entity);

        Assertions.assertEquals(TodoListModel.builder().build(), result);

        Assertions.assertNull(result.getId());
        Assertions.assertNull(result.getName());
        Assertions.assertNull(result.getDescription());
        Assertions.assertNull(result.getUserId());
        Assertions.assertNull(result.getUsername());
        Assertions.assertNull(result.getBase64Image());
        Assertions.assertNull(result.getCreatedAt());
        Assertions.assertNull(result.getModifiedAt());
        Assertions.assertNotNull(result.getItems());
    }

    @Test
    void convertToFull() {
        var name = "name";
        var description = "description";
        var username = "username";
        var base64Image = "base64Image";
        var createdAt = ZonedDateTime.of(1990, 4, 26, 3, 4, 5, 6, ZoneId.systemDefault());
        var modifiedAt = ZonedDateTime.of(2023, 5, 26, 6, 7, 8, 9, ZoneId.systemDefault());

        var item1 = new TodoListItemMysqlEntity();
        item1.setChecked(true);
        item1.setText("text1");
        item1.setId(UUID.randomUUID());
        item1.setPosition(0);
        var item2 = new TodoListItemMysqlEntity();
        item2.setChecked(true);
        item2.setText("text2");
        item2.setId(UUID.randomUUID());
        item2.setPosition(1);

        var entity = new TodoListMysqlEntity();
        entity.setId(ID);
        entity.setName(name);
        entity.setDescription(description);
        entity.setUserId(USER_ID);
        entity.setUserName(username);
        entity.setBase64Image(base64Image);
        entity.setCreatedAt(createdAt);
        entity.setModifiedAt(modifiedAt);
        entity.setItems(List.of(item1, item2));
        item1.setTodoList(entity);
        item2.setTodoList(entity);

        var expected = TodoListModel.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withUserId(USER_ID)
                .withUsername(username)
                .withBase64Image(base64Image)
                .withCreatedAt(createdAt)
                .withModifiedAt(modifiedAt)
                .addItem(TodoListItemModel.builder()
                        .withId(item1.getId())
                        .withText(item1.getText())
                        .withChecked(item1.isChecked())
                        .build())
                .addItem(TodoListItemModel.builder()
                        .withId(item2.getId())
                        .withText(item2.getText())
                        .withChecked(item2.isChecked())
                        .build())
                .build();

        var result = converter.convertTo(entity);
        Assertions.assertEquals(expected, result);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(description, result.getDescription());
        Assertions.assertEquals(USER_ID, result.getUserId());
        Assertions.assertEquals(username, result.getUsername());
        Assertions.assertEquals(base64Image, result.getBase64Image());
        Assertions.assertEquals(createdAt, result.getCreatedAt());
        Assertions.assertEquals(modifiedAt, result.getModifiedAt());
        Assertions.assertEquals(expected.getItems(), result.getItems());
    }

    @Test
    void convertFromEmpty() {
        var model = TodoListModel.builder().build();
        var result = converter.convertFrom(model);

        Assertions.assertNull(result.getId());
        Assertions.assertNull(result.getName());
        Assertions.assertNull(result.getDescription());
        Assertions.assertNull(result.getUserId());
        Assertions.assertNull(result.getUserName());
        Assertions.assertNull(result.getBase64Image());
        Assertions.assertNull(result.getCreatedAt());
        Assertions.assertNull(result.getModifiedAt());
        Assertions.assertNotNull(result.getItems());
    }

    @Test
    void convertFromFull() {
        var name = "name";
        var description = "description";
        var username = "username";
        var base64Image = "base64Image";
        var createdAt = ZonedDateTime.of(1990, 4, 26, 3, 4, 5, 6, ZoneId.systemDefault());
        var modifiedAt = ZonedDateTime.of(2023, 5, 26, 6, 7, 8, 9, ZoneId.systemDefault());

        var model = TodoListModel.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withUserId(USER_ID)
                .withUsername(username)
                .withBase64Image(base64Image)
                .withCreatedAt(createdAt)
                .withModifiedAt(modifiedAt)
                .addItem(TodoListItemModel.builder()
                        .withId(UUID.randomUUID())
                        .withText("text1")
                        .withChecked(true)
                        .build())
                .addItem(TodoListItemModel.builder()
                        .withId(UUID.randomUUID())
                        .withText("text2")
                        .withChecked(true)
                        .build())
                .build();

        var item1 = new TodoListItemMysqlEntity();
        item1.setChecked(true);
        item1.setText("text1");
        item1.setId(model.getItems().get(0).getId());
        item1.setPosition(0);
        var item2 = new TodoListItemMysqlEntity();
        item2.setChecked(true);
        item2.setText("text2");
        item2.setId(model.getItems().get(1).getId());
        item2.setPosition(1);

        var expected = new TodoListMysqlEntity();
        expected.setId(ID);
        expected.setName(name);
        expected.setDescription(description);
        expected.setUserId(USER_ID);
        expected.setUserName(username);
        expected.setBase64Image(base64Image);
        expected.setCreatedAt(createdAt);
        expected.setModifiedAt(modifiedAt);
        expected.setItems(List.of(item1, item2));
        item1.setTodoList(expected);
        item2.setTodoList(expected);

        var result = converter.convertFrom(model);
        Assertions.assertEquals(expected, result);

        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(description, result.getDescription());
        Assertions.assertEquals(USER_ID, result.getUserId());
        Assertions.assertEquals(username, result.getUserName());
        Assertions.assertEquals(base64Image, result.getBase64Image());
        Assertions.assertEquals(createdAt, result.getCreatedAt());
        Assertions.assertEquals(modifiedAt, result.getModifiedAt());
        Assertions.assertEquals(expected.getItems(), result.getItems());
    }
}