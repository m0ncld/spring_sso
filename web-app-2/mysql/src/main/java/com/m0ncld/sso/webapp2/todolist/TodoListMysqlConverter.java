package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.Converter;
import org.springframework.stereotype.Service;

@Service
class TodoListMysqlConverter implements Converter<TodoListModel, TodoListMysqlEntity> {
    @Override
    public TodoListModel convertTo(TodoListMysqlEntity entity) {
        return null;
    }

    @Override
    public TodoListMysqlEntity convertFrom(TodoListModel model) {
        return null;
    }
}
