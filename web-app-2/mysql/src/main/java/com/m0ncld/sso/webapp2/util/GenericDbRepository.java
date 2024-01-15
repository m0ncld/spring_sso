package com.m0ncld.sso.webapp2.util;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Generic database repository
 * @param <M> Model
 * @param <I> Model identifier
 * @param <E> Entity
 * @param <R> Spring database CRUD repository
 */
public class GenericDbRepository<M extends Model<I>, I, E extends Model<I>, R extends CrudRepository<E, I>> implements GenericCrudRepository<M, I> {

    /**
     * Model to entity converter
     */
    protected final Converter<M, E> converter;

    /**
     * Spring database CRUD repository
     */
    protected final R dbRepository;

    /**
     * Constructor of generic database repository
     * @param converter Model to entity converter
     * @param dbRepository Spring database CRUD repository
     */
    public GenericDbRepository(Converter<M, E> converter, R dbRepository) {
        this.converter = converter;
        this.dbRepository = dbRepository;
    }

    /**
     * Save the model converted to entity  using Spring database CRUD repository
     * @param model Model
     * @return Saved model
     */
    @Override
    public M save(M model) {
        return Optional.ofNullable(model)
                .map(converter::convertFrom)
                .map(dbRepository::save)
                .map(converter::convertTo)
                .orElse(null);
    }

    /**
     * Save collection of models converted to entity using Spring database CRUD repository
     * @param models Models
     * @return Collection of saved models
     */
    @Override
    public Collection<M> saveAll(Collection<M> models) {
        return Optional.ofNullable(models)
                .map(converter::convertFrom)
                .map(dbRepository::saveAll)
                .map(converter::convertTo)
                .orElse(Collections.emptyList());
    }

    /**
     * Find the model by identifier using Spring database CRUD repository
     * @param id identifier
     * @return Model
     */
    @Override
    public Optional<M> findById(I id) {
        return Optional.ofNullable(id)
                .map(dbRepository::findById)
                .map(converter::convertTo)
                .orElse(null);
    }

    /**
     * Returns true if the model exist, false if not
     * @param id Model identifier
     * @return True if the model exist, false if not
     */
    @Override
    public boolean existsById(I id) {
        return Optional.ofNullable(id).map(dbRepository::existsById).orElse(false);
    }

    /**
     * Finds all models using Spring database CRUD repository
     * @return All models
     */
    @Override
    public Collection<M> findAll() {
        return converter.convertTo(dbRepository.findAll());
    }

    /**
     * Finds all models of given identifiers using Spring database CRUD repository
     * @param ids Model identifiers
     * @return Collection of models
     */
    @Override
    public Collection<M> findAllById(Collection<I> ids) {
        return Optional.ofNullable(ids)
                .map(dbRepository::findAllById)
                .map(converter::convertTo)
                .orElse(Collections.emptyList());
    }

    /**
     * Returns number of models using Spring database CRUD repository
     * @return Number of models
     */
    @Override
    public long count() {
        return dbRepository.count();
    }

    /**
     * Delete model with given identifier using Spring database CRUD repository
     * @param id Model identifier
     */
    @Override
    public void deleteById(I id) {
        Optional.ofNullable(id).ifPresent(dbRepository::deleteById);
    }

    /**
     * Delete model using Spring database CRUD repository
     * @param model Model
     */
    @Override
    public void delete(M model) {
        Optional.ofNullable(model)
                .map(converter::convertFrom)
                .ifPresent(dbRepository::delete);
    }

    /**
     * Delete all models with given identifiers using Spring database CRUD repository
     * @param ids Identifiers
     */
    @Override
    public void deleteAllById(Collection<I> ids) {
        Optional.ofNullable(ids).ifPresent(dbRepository::deleteAllById);
    }

    /**
     * Delete all models using Spring database CRUD repository
     * @param models Models
     */
    @Override
    public void deleteAll(Collection<M> models) {
        Optional.ofNullable(models)
                .map(converter::convertFrom)
                .ifPresent(dbRepository::deleteAll);
    }

    /**
     * Delete all models using Spring database CRUD repository
     */
    @Override
    public void deleteAll() {
        dbRepository.deleteAll();
    }
}
