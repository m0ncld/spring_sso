package com.m0ncld.sso.webapp2.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SerializationUtilTest {

    @Test
    void unmodifable() {
        var input = new ArrayList<Integer>();
        var result = SerializationUtil.unmodifable(input);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> result.add(1));
    }

    @Test
    void modifable() {
        var input = Collections.emptyList();
        var result = SerializationUtil.modifable(input);
        Assertions.assertDoesNotThrow(() -> result.add(1));
    }

    @Test
    void unmodifableNull() {
        var result = SerializationUtil.unmodifable(null);
        Assertions.assertNull(result);
    }

    @Test
    void modifableNull() {
        var result = SerializationUtil.modifable(null);
        Assertions.assertNull(result);
    }
}