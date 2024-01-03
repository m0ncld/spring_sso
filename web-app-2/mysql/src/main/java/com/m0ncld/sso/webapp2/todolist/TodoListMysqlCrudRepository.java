package com.m0ncld.sso.webapp2.todolist;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface TodoListMysqlCrudRepository extends CrudRepository<TodoListMysqlEntity, UUID> {
}
