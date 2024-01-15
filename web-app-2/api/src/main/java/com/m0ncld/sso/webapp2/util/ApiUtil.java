package com.m0ncld.sso.webapp2.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ApiUtil {

    private static final String WARNING_HEADER = "Warning";

    private ApiUtil() {}

    public static <T> ResponseEntity<T> unauthorized(String warning) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header(WARNING_HEADER, warning)
                .build();
    }

    public static <T> ResponseEntity<T> noContent() {
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<T> ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> ok(@NotNull T body) {
        return ResponseEntity.ok(body);
    }

    public static <T> ResponseEntity<T> of(Optional<T> body) {
        return body.map(ApiUtil::ok)
                .orElseGet(ApiUtil::noContent);
    }

    public static <T> ResponseEntity<T> of(T body) {
        return ApiUtil.of(Optional.ofNullable(body));
    }
}
