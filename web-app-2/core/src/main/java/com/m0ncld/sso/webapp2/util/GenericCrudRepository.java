package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;

/**
 * Generic CRUD repository
 * @param <M> Model
 * @param <I> Model identifier
 */
public interface GenericCrudRepository<M extends Model<I>, I> {

    /**
     * Save the model
     * @param model Model
     * @return Saved model
     */
    M save(M model);

    /**
     * Save collection of models
     * @param models Models
     * @return Collection of saved models
     */
    Collection<M> saveAll(Collection<M> models);

    /**
     * Find the model by identifier
     * @param id identifier
     * @return Model
     */
    Optional<M> findById(I id);

    /**
     * Returns true if the model exist, false if not
     * @param id Model identifier
     * @return True if the model exist, false if not
     */
    boolean existsById(I id);

    /**
     * Finds all models
     * @return All models
     */
    Collection<M> findAll();

    /**
     * Finds all models of given identifiers
     * @param ids Model identifiers
     * @return Collection of models
     */
    Collection<M> findAllById(Collection<I> ids);

    /**
     * Returns number of models
     * @return Number of models
     */
    long count();

    /**
     * Delete model with given identifier
     * @param id Model identifier
     */
    void deleteById(I id);

    /**
     * Delete model
     * @param model Model
     */
    void delete(M model);

    /**
     * Delete all models with given identifiers
     * @param ids Identifiers
     */
    void deleteAllById(Collection<I> ids);

    /**
     * Delete all models
     * @param models Models
     */
    void deleteAll(Collection<M> models);

    /**
     * Delete all models
     */
    void deleteAll();

}
