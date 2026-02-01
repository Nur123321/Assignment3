package com.assignment3.repository.interfaces;

@FunctionalInterface
public interface IdExtractor<T, ID> {
    ID getId(T entity);
}
