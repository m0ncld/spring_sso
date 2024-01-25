package com.m0ncld.sso.webapp2.util;

import net.bytebuddy.matcher.StringMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class GenericCrudControllerTest {

    private final String ID = "id";

    private GenericCrudController<ModelImpl, String, GenericCrudRepository<ModelImpl, String>> instance;

    @Mock
    private GenericCrudRepository<ModelImpl, String> repository;


    @BeforeEach
    void setUp() {
        instance = new GenericCrudController<>(repository);
    }

    @Test
    void save() {
        var input = new ModelImpl();
        var expected = new ModelImpl();
        Mockito.doReturn(expected).when(repository).save(input);
        var result = instance.save(input);
        Assertions.assertSame(expected, result);
    }

    @Test
    void saveAll() {
        var input = List.of(new ModelImpl());
        var expected = List.of(new ModelImpl());
        Mockito.doReturn(expected).when(repository).saveAll(input);
        var result = instance.saveAll(input);
        Assertions.assertSame(expected, result);
    }

    @Test
    void findById() {
        var expected = Optional.of(new ModelImpl());
        Mockito.doReturn(expected).when(repository).findById(ID);
        var result = instance.findById(ID);
        Assertions.assertSame(expected, result);
    }

    @Test
    void existsById() {
        var expected = true;
        Mockito.doReturn(expected).when(repository).existsById(ID);
        var result = instance.existsById(ID);
        Assertions.assertSame(expected, result);
    }

    @Test
    void findAll() {
        var expected = List.of(new ModelImpl());
        Mockito.doReturn(expected).when(repository).findAll();
        var result = instance.findAll();
        Assertions.assertSame(expected, result);
    }

    @Test
    void findAllById() {
        var id = List.of(ID);
        var expected = List.of(new ModelImpl());
        Mockito.doReturn(expected).when(repository).findAllById(id);
        var result = instance.findAllById(id);
        Assertions.assertSame(expected, result);
    }

    @Test
    void count() {
        var expected = 13L;
        Mockito.doReturn(expected).when(repository).count();
        var result = instance.count();
        Assertions.assertSame(expected, result);
    }

    @Test
    void deleteById() {
        instance.deleteById(ID);
        Mockito.verify(repository).deleteById(ID);
    }

    @Test
    void delete() {
        var input = new ModelImpl();
        instance.delete(input);
        Mockito.verify(repository).delete(input);
    }

    @Test
    void deleteAllById() {
        var id = List.of(ID);
        instance.deleteAllById(id);
        Mockito.verify(repository).deleteAllById(id);
    }

    @Test
    void deleteAll() {
        instance.deleteAll();
        Mockito.verify(repository).deleteAll();
    }

    @Test
    void testDeleteAll() {
        var input = List.of(new ModelImpl());
        instance.deleteAll(input);
        Mockito.verify(repository).deleteAll(input);
    }

    public static class ModelImpl extends GenericModel<String> {


        protected ModelImpl() {
            this("id");
        }

        public ModelImpl(String id) {
            super(id);
        }
    }
}