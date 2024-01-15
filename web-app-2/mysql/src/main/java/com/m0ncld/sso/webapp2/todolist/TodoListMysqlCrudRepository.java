package com.m0ncld.sso.webapp2.todolist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.UUID;

/**
 * Todo list database CRUD repository
 */
interface TodoListMysqlCrudRepository extends CrudRepository<TodoListMysqlEntity, UUID> {

    /**
     * Find all todo lists for user with given identifier
     * @param userId User identifier
     * @return Collection of all user todo lists
     */
    @Query("SELECT e from TodoListMysqlEntity e where e.userId = ?1")
    Collection<TodoListMysqlEntity> findAllForUser(UUID userId);
}
