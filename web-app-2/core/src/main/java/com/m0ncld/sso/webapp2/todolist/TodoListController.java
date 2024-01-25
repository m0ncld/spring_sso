package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.util.GenericCrudController;
import com.m0ncld.sso.webapp2.util.GenericModel;
import org.springframework.stereotype.Controller;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Todo list controller
 */
@Controller
public class TodoListController extends GenericCrudController<TodoListModel, UUID, TodoListCrudRepository> {

    protected TodoListController(TodoListCrudRepository repository) {
        super(repository);
    }


    /**
     * Saves the todo list
     * @param model Todo list model
     * @return Created or modified todo list model
     */
    @Override
    public TodoListModel save(TodoListModel model) {
        var builder = model.builderOf()
                .withModifiedAt(ZonedDateTime.now())
                .withCreatedAt(
                        Optional.ofNullable(model.getId())
                                .flatMap(repository::findById)
                                .map(TodoListModel::getCreatedAt)
                                .orElseGet(ZonedDateTime::now)
                );
        return super.save(builder.build());
    }

    /**
     * Saves the collection of todo list
     * @param models Collection of todo lists for save
     * @return Collection of saved todo lists
     */
    @Override
    public Collection<TodoListModel> saveAll(Collection<TodoListModel> models) {
        var ids = models.stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
        var persisted = repository.findAllById(ids).stream()
                .collect(Collectors.toMap(TodoListModel::getId, model -> model));
        var newModels = models.stream()
                .map(model -> model.builderOf()
                        .withCreatedAt(
                                Optional.ofNullable(model.getId())
                                        .filter(persisted::containsKey)
                                        .map(persisted::get)
                                        .map(TodoListModel::getCreatedAt).orElse(ZonedDateTime.now())))
                .map(builder -> builder.withModifiedAt(ZonedDateTime.now()))
                .map(TodoListModel.Builder::build)
                .collect(Collectors.toList());

        return super.saveAll(newModels);
    }

    /**
     * Get collection of all todo lists created by user with given identifier
     * @param userId User identifier
     * @return Collection of all todolists created by user with fiven identifier
     */
    public Collection<TodoListModel> findAllForUser(UUID userId) {
        return repository.findAllForUser(userId);
    }
}
