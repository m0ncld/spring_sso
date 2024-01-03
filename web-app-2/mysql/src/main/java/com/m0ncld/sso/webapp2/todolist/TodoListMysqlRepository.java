package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.GenericDbRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class TodoListMysqlRepository
        extends GenericDbRepository<TodoListModel, UUID, TodoListMysqlEntity> {

    TodoListMysqlRepository(TodoListMysqlConverter converter, TodoListMysqlCrudRepository dbRepository) {
        super(converter, dbRepository);
    }

}
