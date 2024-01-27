package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;

/**
 * Generic CRUD repository
 * @param <M> Model
 * @param <I> Model identifier
 */
public interface GenericCrudRepository<M extends Model<I>, I> extends GenericReadRepository<M, I> {

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
