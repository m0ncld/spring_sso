package com.m0ncld.sso.webapp2.util;

import java.util.Collection;
import java.util.Optional;

public interface GenericReadRepository<M extends Model<I>, I> {

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
}
