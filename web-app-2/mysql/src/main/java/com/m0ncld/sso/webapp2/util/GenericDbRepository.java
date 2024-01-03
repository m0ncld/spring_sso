package com.m0ncld.sso.webapp2.util;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class GenericDbRepository<M extends Model<I>, I, E extends Model<I>> implements GenericCrudRepository<M, I> {

    protected final Converter<M, E> converter;
    protected final CrudRepository<E, I> dbRepository;

    public GenericDbRepository(Converter<M, E> converter, CrudRepository<E, I> dbRepository) {
        this.converter = converter;
        this.dbRepository = dbRepository;
    }


    @Override
    public M save(M model) {
        return Optional.ofNullable(model)
                .map(converter::convertFrom)
                .map(dbRepository::save)
                .map(converter::convertTo)
                .orElse(null);
    }

    @Override
    public Iterable<M> saveAll(Collection<M> models) {
        return Optional.ofNullable(models)
                .map(converter::convertFrom)
                .map(dbRepository::saveAll)
                .map(converter::convertTo)
                .orElse(Collections.emptyList());
    }

    @Override
    public Optional<M> findById(I id) {
        return Optional.ofNullable(id)
                .map(dbRepository::findById)
                .map(converter::convertTo)
                .orElse(null);
    }

    @Override
    public boolean existsById(I id) {
        return Optional.ofNullable(id).map(dbRepository::existsById).orElse(false);
    }

    @Override
    public Iterable<M> findAll() {
        return converter.convertTo(dbRepository.findAll());
    }

    @Override
    public Iterable<M> findAllById(Collection<I> ids) {
        return Optional.ofNullable(ids)
                .map(dbRepository::findAllById)
                .map(converter::convertTo)
                .orElse(Collections.emptyList());
    }

    @Override
    public long count() {
        return dbRepository.count();
    }

    @Override
    public void deleteById(I id) {
        Optional.ofNullable(id).ifPresent(dbRepository::deleteById);
    }

    @Override
    public void delete(M model) {
        Optional.ofNullable(model)
                .map(converter::convertFrom)
                .ifPresent(dbRepository::delete);
    }

    @Override
    public void deleteAllById(Iterable<I> ids) {
        Optional.ofNullable(ids).ifPresent(dbRepository::deleteAllById);
    }

    @Override
    public void deleteAll(Iterable<M> models) {
        Optional.ofNullable(models)
                .map(converter::convertFrom)
                .ifPresent(dbRepository::deleteAll);
    }

    @Override
    public void deleteAll() {
        dbRepository.deleteAll();
    }
}
