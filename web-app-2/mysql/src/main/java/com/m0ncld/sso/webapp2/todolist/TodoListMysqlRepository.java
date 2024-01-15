package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.GenericDbRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

/**
 * Implementation of Todo list repository
 */
@Repository
class TodoListMysqlRepository
        extends GenericDbRepository<TodoListModel, UUID, TodoListMysqlEntity, TodoListMysqlCrudRepository>
        implements TodoListCrudRepository {

    /**
     * Construcor of Todo list database repository
     * @param converter Todo list model to entity converter
     * @param dbRepository Todo list CRUD database repository
     */
    TodoListMysqlRepository(TodoListMysqlConverter converter, TodoListMysqlCrudRepository dbRepository) {
        super(converter, dbRepository);
    }

    /**
     * Find collection of all todo lists created by user with given identifier
     * @param userId User indentifier
     * @return Collection of all todo lists created by user with given identifier
     */
    @Override
    public Collection<TodoListModel> findAllForUser(UUID userId) {
        return converter.convertTo(dbRepository.findAllForUser(userId));
    }
}
