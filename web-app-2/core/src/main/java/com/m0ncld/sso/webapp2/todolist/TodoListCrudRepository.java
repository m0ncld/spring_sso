package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.GenericCrudRepository;

import java.util.Collection;
import java.util.UUID;

/**
 * CRUD Todo list repository
 */
interface TodoListCrudRepository extends GenericCrudRepository<TodoListModel, UUID> {

    /**
     * Find collection of all todo lists created by user with given identifier
     * @param userId User indentifier
     * @return Collection of all todo lists created by user with given identifier
     */
    Collection<TodoListModel> findAllForUser(UUID userId);
}
