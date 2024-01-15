package com.m0ncld.sso.webapp2.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ApiUtilTest {

    @Test
    void unauthorized() {
        var expected = "warning text";
        var result = ApiUtil.unauthorized(expected);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        Assertions.assertNull(result.getBody());
        Assertions.assertEquals(1, result.getHeaders().get("Warning").size());
        Assertions.assertEquals(expected, result.getHeaders().get("Warning").get(0));
    }

    @Test
    void noContent() {
        var result = ApiUtil.noContent();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void ok() {
        var expected = new Object();
        var result = ApiUtil.ok(expected);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(expected, result.getBody());
    }

    @Test
    void of() {
        var expected = new Object();
        var result = ApiUtil.of(expected);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(expected, result.getBody());
    }

    @Test
    void ofNull() {
        Object expected = null;
        var result = ApiUtil.of(expected);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Assertions.assertNull(result.getBody());
    }
}