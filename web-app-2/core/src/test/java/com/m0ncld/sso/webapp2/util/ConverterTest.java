package com.m0ncld.sso.webapp2.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    private ConverterImpl instance;

    @BeforeEach
    void setUp() {
        instance = new ConverterImpl();
    }

    @Test
    void convertTo() {
        var result = instance.convertTo(Optional.of(10));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("10", result.get());
    }

    @Test
    void convertToEmpty() {
        var result = instance.convertTo(Optional.empty());
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testConvertTo() {
        var result = instance.convertTo(List.of(10, 11,13));
        Assertions.assertEquals(List.of("10", "11", "13"), result);
    }

    @Test
    void convertFrom() {
        var result = instance.convertFrom(Optional.of("10"));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(10, result.get());
    }

    @Test
    void convertFromEmpty() {
        var result = instance.convertFrom(Optional.empty());
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testConvertFrom() {
        var result = instance.convertFrom(List.of("10", "11","13"));
        Assertions.assertEquals(List.of(10, 11, 13), result);
    }

    @Test
    void getIdOrGenerate() {
        var id = UUID.fromString("285ffa74-5174-451c-8cb6-72ac0f176aff");
        var result = instance.getIdOrGenerate(id);
        Assertions.assertEquals(id, result);
    }

    @Test
    void getIdOrGenerateNull() {
        var result = instance.getIdOrGenerate(null);
        Assertions.assertNotNull(result);
    }

    static class ConverterImpl implements Converter<String, Integer> {
        @Override
        public String convertTo(Integer entity) {
            return Integer.toString(entity);
        }

        @Override
        public Integer convertFrom(String model) {
            return Integer.valueOf(model);
        }
    }
}