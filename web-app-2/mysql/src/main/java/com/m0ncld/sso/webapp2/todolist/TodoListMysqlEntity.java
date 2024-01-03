package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.Model;

import java.util.UUID;

public class TodoListMysqlEntity implements Model<UUID> {

    private UUID id;

    @Override
    public UUID getId() {
        return id;
    }

    public TodoListMysqlEntity setId(UUID id) {
        this.id = id;
        return this;
    }
}
