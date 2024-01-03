package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.GenericCrudRepository;

import java.util.UUID;

interface TodoListCrudRepository extends GenericCrudRepository<TodoListModel, UUID> {
}
