package com.m0ncld.sso.webapp2.util;

import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Optional;

/**
 * Generic CRUD controller
 * @param <M> Model
 * @param <I> Model identifier
 * @param <R> Model CRUD repository
 */
public class GenericCrudController<M extends Model<I>, I, R extends GenericCrudRepository<M, I>> {

    /**
     * Model CRUD repository
     */
    protected final R repository;

    protected GenericCrudController(R repository) {
        this.repository = repository;
    }

    /**
     * Save the model
     * @param model Model
     * @return Saved model
     */
    public M save(@Validated M model) {
        return repository.save(model);
    }

    /**
     * Save collection of models
     * @param models Models
     * @return Collection of saved models
     */
    public Collection<M> saveAll(@Validated Collection<M> models) {
        return repository.saveAll(models);
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

    /**
     * Delete model with given identifier
     * @param id Model identifier
     */
    public void deleteById(I id) {
        repository.deleteById(id);
    }

    /**
     * Delete model
     * @param model Model
     */
    public void delete(M model) {
        repository.delete(model);
    }

    /**
     * Delete all models with given identifiers
     * @param ids Identifiers
     */
    public void deleteAllById(Collection<I> ids) {
        repository.deleteAllById(ids);
    }

    /**
     * Delete all models
     * @param models Models
     */
    public void deleteAll(Collection<M> models) {
        repository.deleteAll(models);
    }

    /**
     * Delete all models
     */
    public void deleteAll() {
        repository.deleteAll();
    }


}
