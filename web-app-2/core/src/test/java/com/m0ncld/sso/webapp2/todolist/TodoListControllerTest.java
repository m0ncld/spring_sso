package com.m0ncld.sso.webapp2.todolist;

import com.m0ncld.sso.webapp2.todolist.TodoListController;
import com.m0ncld.sso.webapp2.todolist.TodoListCrudRepository;
import com.m0ncld.sso.webapp2.todolist.TodoListModel;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoListControllerTest {

    private static final UUID ID = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");

    private TodoListController instance;

    @Mock
    private TodoListCrudRepository repository;

    @BeforeEach
    void setUp() {
        instance = new TodoListController(repository);
    }

    @Test
    void save() {
        var input =  TodoListModel.builder()
                .build();
        var expected = TodoListModel.builder().build();
        Mockito.doReturn(expected).when(repository).save(Mockito.any());

        var result = instance.save(input);
        Assertions.assertSame(expected, result);

        var captor = ArgumentCaptor.forClass(TodoListModel.class);
        Mockito.verify(repository).save(captor.capture());
        var captured = captor.getValue();
        Assertions.assertNotNull(captured.getModifiedAt());
        Assertions.assertNotNull(captured.getCreatedAt());
    }

    @Test
    void update() {
        var createdAt = ZonedDateTime.of(1990, 4, 26, 14, 15, 34, 334, ZoneId.systemDefault());
        var input =  TodoListModel
                .builder()
                .withId(ID)
                .build();
        var saved =  TodoListModel
                .builder()
                .withId(ID)
                .withCreatedAt(createdAt)
                .build();

        var expected = TodoListModel.builder().build();
        Mockito.doReturn(expected).when(repository).save(Mockito.any());
        Mockito.doReturn(Optional.of(saved)).when(repository).findById(ID);

        var result = instance.save(input);
        Assertions.assertSame(expected, result);

        var captor = ArgumentCaptor.forClass(TodoListModel.class);
        Mockito.verify(repository).save(captor.capture());
        var captured = captor.getValue();
        Assertions.assertNotNull(captured.getModifiedAt());
        Assertions.assertEquals(createdAt, captured.getCreatedAt());
    }

    @Test
    void updateNotFound() {
        var input =  TodoListModel
                .builder()
                .withId(ID)
                .build();

        var expected = TodoListModel.builder().build();
        Mockito.doReturn(expected).when(repository).save(Mockito.any());
        Mockito.doReturn(Optional.empty()).when(repository).findById(ID);

        var result = instance.save(input);
        Assertions.assertSame(expected, result);

        var captor = ArgumentCaptor.forClass(TodoListModel.class);
        Mockito.verify(repository).save(captor.capture());
        var captured = captor.getValue();
        Assertions.assertNotNull(captured.getModifiedAt());
        Assertions.assertNotNull(captured.getCreatedAt());
    }

    @Test
    @SuppressWarnings("unchecked")
    void saveAll() {
        var input =  List.of(TodoListModel.builder()
                .build());
        var expected = List.of(TodoListModel.builder().build());
        Mockito.doReturn(expected).when(repository).saveAll(Mockito.any());

        var result = instance.saveAll(input);
        Assertions.assertSame(expected, result);

        var captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(repository).saveAll(captor.capture());
        var captured = (List<TodoListModel>) captor.getValue();
        Assertions.assertEquals(1, captured.size());
        Assertions.assertNotNull(captured.get(0).getModifiedAt());
        Assertions.assertNotNull(captured.get(0).getCreatedAt());
    }

    @Test
    @SuppressWarnings("unchecked")
    void updateAll() {
        var createdAt = ZonedDateTime.of(1990, 4, 26, 14, 15, 34, 334, ZoneId.systemDefault());
        var input =  List.of(TodoListModel
                .builder()
                .withId(ID)
                .build());
        var saved =  List.of(TodoListModel
                .builder()
                .withId(ID)
                .withCreatedAt(createdAt)
                .build());

        var expected = List.of(TodoListModel.builder().build());
        Mockito.doReturn(expected).when(repository).saveAll(Mockito.any());
        Mockito.doReturn(saved).when(repository).findAllById(Mockito.eq(List.of(ID)));

        var result = instance.saveAll(input);
        Assertions.assertSame(expected, result);

        var captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(repository).saveAll(captor.capture());
        var captured = (List<TodoListModel>) captor.getValue();
        Assertions.assertEquals(1, captured.size());
        Assertions.assertNotNull(captured.get(0).getModifiedAt());
        Assertions.assertEquals(createdAt, captured.get(0).getCreatedAt());
    }

    @Test
    @SuppressWarnings("unchecked")
    void updateNotFoundAll() {
        var input =  List.of(TodoListModel
                .builder()
                .withId(ID)
                .build());

        var expected = List.of(TodoListModel.builder().build());
        Mockito.doReturn(expected).when(repository).saveAll(Mockito.any());
        Mockito.doReturn(Collections.emptyList()).when(repository).findAllById(Mockito.eq(List.of(ID)));

        var result = instance.saveAll(input);
        Assertions.assertSame(expected, result);

        var captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(repository).saveAll(captor.capture());
        var captured = (List<TodoListModel>) captor.getValue();
        Assertions.assertEquals(1, captured.size());
        Assertions.assertNotNull(captured.get(0).getModifiedAt());
        Assertions.assertNotNull(captured.get(0).getCreatedAt());
    }

    @Test
    void findAllForUser() {
        var expected = List.of(TodoListModel.builder().build());
        Mockito.doReturn(expected).when(repository).findAllForUser(ID);
        var result = instance.findAllForUser(ID);
        Assertions.assertSame(expected, result);
    }
}