package com.m0ncld.sso.webapp2.util;

/**
 * Model with iodentifier interface
 * @param <I> Identifier
 */
public interface Model<I> {

    /**
     * Returns model itentifier
     * @return Model identifier
     */
    I getId();

}
