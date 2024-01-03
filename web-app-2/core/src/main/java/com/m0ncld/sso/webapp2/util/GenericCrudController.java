package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;

public class GenericCrudController<M extends Model<I>, I, R extends GenericCrudRepository<M, I>> {

    protected final R repository;

    protected GenericCrudController(R repository) {
        this.repository = repository;
    }

    public M save(M model) {
        return repository.save(model);
    }

    public Iterable<M> saveAll(Collection<M> models) {
        return repository.saveAll(models);
    }

    public Optional<M> findById(I id) {
        return repository.findById(id);
    }

    public boolean existsById(I id) {
        return repository.existsById(id);
    }

    public Iterable<M> findAll() {
        return repository.findAll();
    }

    public Iterable<M> findAllById(Collection<I> ids) {
        return repository.findAllById(ids);
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(I id) {
        repository.deleteById(id);
    }

    public void delete(M model) {
        repository.delete(model);
    }

    public void deleteAllById(Iterable<? extends I> ids) {
        repository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends M> models) {
        repository.deleteAll(models);
    }

    public void deleteAll() {
        repository.deleteAll();
    }


}
