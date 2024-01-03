package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.GenericCrudController;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class TodoListController extends GenericCrudController<TodoListModel, UUID, TodoListCrudRepository> {

    protected TodoListController(TodoListCrudRepository repository) {
        super(repository);
    }
}
