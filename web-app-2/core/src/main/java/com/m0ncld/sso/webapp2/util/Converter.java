package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Generic converter interface
 * @param <M> Model
 * @param <E> Entity
 */
public interface Converter<M, E> {

    /**
     * Converts entity to model
     * @param entity Entity
     * @return Converted model
     */
    M convertTo(E entity);

    /**
     * Converts optional value of entity to optional value of model
     * @param entity Optional value of entity
     * @return Optional value of converted model
     */
    default Optional<M> convertTo(Optional<E> entity) {
        return entity.map(this::convertTo);
    }

    /**
     * Converts iterable entities to collection of models
     * @param entities Iterable entities
     * @return Collection of models
     */
    default Collection<M> convertTo(Iterable<E> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    /**
     * Converts model to entity
     * @param model Model
     * @return Converted entity
     */
    E convertFrom(M model);

    /**
     * Converts optional value of model to optional value of entity
     * @param model Optional value of model
     * @return Optional value of converted entity
     */
    default Optional<E> convertFrom(Optional<M> model) {
        return model.map(this::convertFrom);
    }

    /**
     * Converts iterable models to collection of entities
     * @param models Iterable models
     * @return Collection of entities
     */
    default Collection<E> convertFrom(Iterable<M> models) {
        return StreamSupport.stream(models.spliterator(), false)
                .map(this::convertFrom)
                .collect(Collectors.toList());
    }

    /**
     * Returns given uuid identifier or generate new one
     * @param id Identifier
     * @return Identifier if not null or UUID.randomUUID
     */
    default UUID getIdOrGenerate(UUID id) {
        return Optional.ofNullable(id).orElseGet(UUID::randomUUID);
    }

}
