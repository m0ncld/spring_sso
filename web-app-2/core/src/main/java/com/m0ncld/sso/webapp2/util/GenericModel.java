package com.m0ncld.sso.webapp2.util;

import java.util.Objects;

public class GenericModel<I> implements Model<I> {

    protected final I id;

    protected GenericModel(I id) {
        this.id = id;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericModel<?> that = (GenericModel<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GenericModel{" +
                "id=" + id +
                '}';
    }
}
