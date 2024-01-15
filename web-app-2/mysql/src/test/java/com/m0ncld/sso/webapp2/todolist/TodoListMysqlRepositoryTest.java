package com.m0ncld.sso.webapp2.todolist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TodoListMysqlRepositoryTest {

    private final UUID ID = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");

    private TodoListMysqlRepository instance;

    @Mock
    private TodoListMysqlCrudRepository dbRepository;

    @Mock
    private TodoListMysqlConverter converter;

    @BeforeEach
    void setUp() {
        instance = new TodoListMysqlRepository(converter, dbRepository);
    }

    @Test
    void findAllForUser() {
        var entities = List.of(new TodoListMysqlEntity());
        var models = List.of(TodoListModel.builder().build());

        Mockito.doReturn(entities).when(dbRepository).findAllForUser(ID);
        Mockito.doReturn(models).when(converter).convertTo(entities);

        var result = instance.findAllForUser(ID);
        Assertions.assertSame(models, result);

        var captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(converter).convertTo(captor.capture());
        Assertions.assertSame(entities, captor.getValue());
    }
}