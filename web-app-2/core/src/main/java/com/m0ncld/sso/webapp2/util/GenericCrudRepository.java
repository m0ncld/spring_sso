package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;

public interface GenericCrudRepository<M extends Model<I>, I> {

    M save(M model);

    Iterable<M> saveAll(Collection<M> models);

    Optional<M> findById(I id);

    boolean existsById(I id);

    Iterable<M> findAll();

    Iterable<M> findAllById(Collection<I> ids);

    long count();

    void deleteById(I id);

    void delete(M model);

    void deleteAllById(Iterable<I> ids);

    void deleteAll(Iterable<M> models);

    void deleteAll();

}
