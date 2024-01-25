package com.m0ncld.sso.webapp2.todolist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TodoListItemModelTest {

    private final UUID ID = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");

    @Test
    void emptyBuilder() {
        var result = TodoListItemModel.builder().build();
        Assertions.assertNull(result.getId());
        Assertions.assertNull(result.getText());
        Assertions.assertFalse(result.isChecked());
    }

    @Test
    void fullBuilder() {
        var text = "text item";
        var checked = true;

        var result = TodoListItemModel.builder()
                .withId(ID)
                .withText(text)
                .withChecked(checked)
                .build();
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(text, result.getText());
        Assertions.assertTrue(result.isChecked());
    }

    @Test
    void builderOf() {
        var text = "text item";
        var checked = true;

        var model = TodoListItemModel.builder()
                .withId(ID)
                .withText(text)
                .withChecked(checked)
                .build();

        var result = model.builderOf().build();
        Assertions.assertEquals(result, model);
        Assertions.assertEquals(ID, result.getId());
        Assertions.assertEquals(text, result.getText());
        Assertions.assertTrue(result.isChecked());

    }
}