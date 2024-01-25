package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Converter todo list model to database entity
 */
@Service
class TodoListMysqlConverter implements Converter<TodoListModel, TodoListMysqlEntity> {

    /**
     * Converts the todo list entity to model
     * @param entity Todo list entity
     * @return Todo list model
     */
    @Override
    public TodoListModel convertTo(TodoListMysqlEntity entity) {
        return TodoListModel.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withBase64Image(entity.getBase64Image())
                .withCreatedAt(entity.getCreatedAt())
                .withModifiedAt(entity.getModifiedAt())
                .withUserId(entity.getUserId())
                .withUsername(entity.getUserName())
                .withItems(entity.getItems()
                        .stream()
                        .map(this::convertTo)
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * Converts todo list item entity to model
     * @param entity Todo list item entity
     * @return Todo list item model
     */
    private TodoListItemModel convertTo(TodoListItemMysqlEntity entity) {
        return TodoListItemModel.builder()
                .withId(entity.getId())
                .withText(entity.getText())
                .withChecked(entity.isChecked())
                .build();
    }

    /**
     * Converts todo list model to entity
     * @param model Todo list model
     * @return Todo list entity
     */
    @Override
    public TodoListMysqlEntity convertFrom(TodoListModel model) {
        var entity = new TodoListMysqlEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setBase64Image(model.getBase64Image());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setModifiedAt(model.getModifiedAt());
        entity.setUserId(model.getUserId());
        entity.setUserName(model.getUsername());
        entity.setItems(new ArrayList<>());
        for (var i = 0; i < model.getItems().size(); i++) {
            var child = convertFrom(model.getItems().get(i));
            child.setPosition(i);
            child.setTodoList(entity);
            entity.getItems().add(child);
        }
        return entity;
    }

    /**
     * Converts todo list item model to entty
     * @param model Todo list item model
     * @return Todo list item entity
     */
    private TodoListItemMysqlEntity convertFrom(TodoListItemModel model) {
        var entity = new TodoListItemMysqlEntity();
        entity.setId(model.getId());
        entity.setText(model.getText());
        entity.setChecked(model.isChecked());
        return entity;
    }
}
