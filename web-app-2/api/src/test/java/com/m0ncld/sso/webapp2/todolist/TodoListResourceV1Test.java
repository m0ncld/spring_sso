package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.testutil.MockPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TodoListResourceV1Test {

    private TodoListResourceV1 instance;
    private MockPrincipal principal;
    private static final UUID USER_ID = UUID.fromString("268b4f9f-df5d-4f97-a520-86bf9a06c67b");
    private static final UUID ID = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");

    @Mock
    private TodoListController controller;

    @BeforeEach
    void setUp() {
        instance = new TodoListResourceV1(controller);
        principal = new MockPrincipal();
        principal.setName(USER_ID.toString());
    }

    @Test
    void getLists() {
        var models = List.of(TodoListModel.builder().build());
        Mockito.doReturn(models).when(controller).findAllForUser(Mockito.any());

        var result = instance.getLists(principal);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(models, result.getBody());
        Mockito.verify(controller).findAllForUser(USER_ID);
    }

    @Test
    void getListsNullPrincipal() {
        var result = instance.getLists(null);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertTrue(result.getBody().isEmpty());
    }

    @Test
    void getListsNullId() {
        principal.setName(null);

        var result = instance.getLists(null);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertTrue(result.getBody().isEmpty());
    }

    @Test
    void getListsNullResult() {
        List<TodoListModel> models = null;
        Mockito.doReturn(models).when(controller).findAllForUser(Mockito.any());

        var result = instance.getLists(principal);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertTrue(result.getBody().isEmpty());
        Mockito.verify(controller).findAllForUser(USER_ID);
    }

    @Test
    void save() {
        var name = "name";
        var description = "description";
        var base64Image = "base64Image";
        var items = List.of(TodoListItemModel.builder().build());
        var input = TodoListInput.builder()
                .withId(ID)
                .withName(name)
                .withDescription(description)
                .withBase64Image(base64Image)
                .withItems(items)
                .build();
        var model = TodoListModel.builder().build();
        Mockito.doReturn(model).when(controller).save(Mockito.any());

        var result = instance.save(input, principal);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertSame(model, result.getBody());
        var captor = ArgumentCaptor.forClass(TodoListModel.class);
        Mockito.verify(controller).save(captor.capture());
        var captured = captor.getValue();
        Assertions.assertEquals(ID, captured.getId());
        Assertions.assertEquals(name, captured.getName());
        Assertions.assertEquals(description, captured.getDescription());
        Assertions.assertEquals(base64Image, captured.getBase64Image());
        Assertions.assertEquals(items, captured.getItems());
        Assertions.assertEquals(USER_ID, captured.getUserId());
    }

    @Test
    void saveNullPrincipal() {
        var input = TodoListInput.builder().build();

        var result = instance.save(input, null);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    void saveNullPrincipalName() {
        var input = TodoListInput.builder().build();
        principal.setName(null);

        var result = instance.save(input, principal);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    void delete() {
        var stored = TodoListModel.builder().withUserId(USER_ID).build();
        Mockito.doReturn(Optional.of(stored)).when(controller).findById(ID);

        var result = instance.delete(ID, principal);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Mockito.verify(controller).findById(ID);
        var captor = ArgumentCaptor.forClass(TodoListModel.class);
        Mockito.verify(controller).delete(captor.capture());
        Assertions.assertEquals(stored, captor.getValue());
    }

    @Test
    void deleteNoPrincipal() {
        var result = instance.delete(ID, principal);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void deleteNoPrincipalNoName() {
        principal.setName(null);
        var result = instance.delete(ID, principal);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    void deleteNotUserList() {
        var stored = TodoListModel.builder().withUserId(ID).build();
        Mockito.doReturn(Optional.of(stored)).when(controller).findById(ID);

        var result = instance.delete(ID, principal);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        Mockito.verify(controller, Mockito.never()).delete(Mockito.any());
    }

    @Test
    void deleteNoList() {
        Mockito.doReturn(Optional.empty()).when(controller).findById(ID);

        var result = instance.delete(ID, principal);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Mockito.verify(controller, Mockito.never()).delete(Mockito.any());
    }

    @Test
    void getListsByAdmin() {
        var models = List.of(TodoListModel.builder().build());
        Mockito.doReturn(models).when(controller).findAll();

        var result = instance.getListsByAdmin();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(models, result.getBody());
        Mockito.verify(controller).findAll();
    }

    @Test
    void saveByAdmin() {
        var input = TodoListModel.builder().build();
        var expected = TodoListModel.builder().build();
        Mockito.doReturn(expected).when(controller).save(input);

        var result = instance.saveByAdmin(input);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertSame(expected, result.getBody());
        Mockito.verify(controller).save(input);
    }

    @Test
    void deleteByAdmin() {
        var result = instance.deleteByAdmin(ID);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Mockito.verify(controller).deleteById(ID);
    }
}