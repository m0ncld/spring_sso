package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;

public class GenericReadController<M extends Model<I>, I, R extends GenericReadRepository<M, I>> {

    /**
     * Model CRUD repository
     */
    protected final R repository;

    protected GenericReadController(R repository) {
        this.repository = repository;
    }

    /**
     * Find the model by identifier
     * @param id identifier
     * @return Model
     */
    public Optional<M> findById(I id) {
        return repository.findById(id);
    }

    /**
     * Returns true if the model exist, false if not
     * @param id Model identifier
     * @return True if the model exist, false if not
     */
    public boolean existsById(I id) {
        return repository.existsById(id);
    }

    /**
     * Finds all models
     * @return All models
     */
    public Collection<M> findAll() {
        return repository.findAll();
    }

    /**
     * Finds all models of given identifiers
     * @param ids Model identifiers
     * @return Collection of models
     */
    public Collection<M> findAllById(Collection<I> ids) {
        return repository.findAllById(ids);
    }

    /**
     * Returns number of models
     * @return Number of models
     */
    public long count() {
        return repository.count();
    }
}
