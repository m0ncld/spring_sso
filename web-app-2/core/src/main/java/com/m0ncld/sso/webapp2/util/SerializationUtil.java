package com.m0ncld.sso.webapp2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Serialization utility class
 */
public class SerializationUtil {

    private SerializationUtil() {};

    /**
     * Convert list to unmodifable list
     * @param source Source modifable list
     * @return Unmodifable list
     * @param <T> List type
     */
    public static <T> List<T> unmodifable(List<T> source) {
        return Optional.ofNullable(source)
                .map(Collections::unmodifiableList)
                .orElse(null);
    }

    /**
     * Convert list to modifable list
     * @param source Unmodifable listy
     * @return Modifable list
     * @param <T> List type
     */
    public static <T> List<T> modifable(List<T> source) {
        return Optional.ofNullable(source)
                .map(ArrayList::new)
                .orElse(null);
    }
}
