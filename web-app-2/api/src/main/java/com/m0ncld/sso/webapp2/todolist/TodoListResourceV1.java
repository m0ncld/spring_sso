package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.config.AuthoritiesConstants;
import com.m0ncld.sso.webapp2.util.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

/**
 * Resource for managing the todo list model
 */
@RestController
@RequestMapping("api/v1/rest/todolist")
class TodoListResourceV1 {

    /**
     * Todo list controller
     */
    private final TodoListController controller;

    TodoListResourceV1(TodoListController controller) {
        this.controller = controller;
    }

    /**
     * Get all user todo lists
     * @param principal User principal
     * @return All user created todo lists
     */
    @GetMapping
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    ResponseEntity<Collection<TodoListModel>> getLists(Principal principal) {
        return ApiUtil.of(
                Optional.ofNullable(principal)
                        .map(Principal::getName)
                        .map(UUID::fromString)
                        .map(controller::findAllForUser)
                        .orElse(Collections.emptyList()));
    }

    /**
     * Save a user todo list
     * @param input User todo list
     * @param principal User principal
     * @return Created todo list model
     */
    @PutMapping
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    ResponseEntity<TodoListModel> save(@RequestBody @Validated TodoListInput input, Principal principal) {
        var userId = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(UUID::fromString);
        if (userId.isEmpty()) {
            return ApiUtil.unauthorized("User identifier not found");
        }
        var modelBuilder = input.toModelBuilder()
                .withUserId(userId.get());
        return ApiUtil.of(controller.save(modelBuilder.build()));
    }

    /**
     * Delete the user todo list with given identifier
     * @param listId Todo list identifier
     * @param principal User principal
     * @return Response entity with HTTP code 204 for successful delete and HTTP code 401 if the todo list was not created by user
     */
    @DeleteMapping("/{listId}")
    @Secured({AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
    ResponseEntity<Object> delete(@PathVariable UUID listId, Principal principal) {
        var userId = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(UUID::fromString);
        if (userId.isEmpty()) {
            return ApiUtil.unauthorized("User ID not found");
        }
        var fetchedList = controller.findById(listId);
        if (fetchedList.isEmpty()) {
            return ApiUtil.noContent();
        }
        return fetchedList
                .filter(model -> userId.get().equals(model.getUserId()))
                .map(model -> {
                    controller.delete(model);
                    return ApiUtil.ok();
                })
                .orElseGet(() -> ApiUtil.unauthorized("User not authorized to delete model"));

    }

    /**
     * Get all created todo lists
     * @return All created list
     */
    @GetMapping("/admin")
    @Secured(AuthoritiesConstants.ADMIN)
    ResponseEntity<Collection<TodoListModel>> getListsByAdmin() {
        return ApiUtil.of(controller.findAll());
    }

    /**
     * Save the todo list
     * @param input Saved list
     * @return Saved todo list
     */
    @PutMapping("/admin")
    @Secured(AuthoritiesConstants.USER)
    ResponseEntity<TodoListModel> saveByAdmin(@RequestBody @Validated TodoListModel input) {
        return ApiUtil.of(controller.save(input));
    }

    /**
     * Delete the todo list
     * @param listId Todo list identifier
     */
    @DeleteMapping("/admin/{listId}")
    ResponseEntity<Void> deleteByAdmin(@PathVariable UUID listId) {
        controller.deleteById(listId);
        return ApiUtil.noContent();
    }
}
