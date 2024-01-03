package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface Converter<M, E> {

    M convertTo(E entity);

    default Optional<M> convertTo(Optional<E> entity) {
        return entity.map(this::convertTo);
    }

    default Collection<M> convertTo(Iterable<E> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    E convertFrom(M model);

    default Optional<E> convertFrom(Optional<M> model) {
        return model.map(this::convertFrom);
    }

    default Collection<E> convertFrom(Iterable<M> models) {
        return StreamSupport.stream(models.spliterator(), false)
                .map(this::convertFrom)
                .collect(Collectors.toList());
    }

}
