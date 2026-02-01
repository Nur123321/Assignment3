package com.assignment3.repository;

import com.assignment3.exception.DuplicateResourceException;
import com.assignment3.exception.ResourceNotFoundException;
import com.assignment3.repository.interfaces.CrudRepository;
import com.assignment3.repository.interfaces.IdExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T, ID> implements CrudRepository<T, ID> {
    private final Map<ID, T> storage = new ConcurrentHashMap<>();
    private final IdExtractor<T, ID> idExtractor;

    public InMemoryRepository(IdExtractor<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public T create(T entity) {
        ID id = idExtractor.getId(entity);
        if (storage.containsKey(id)) {
            throw new DuplicateResourceException("Entity with id " + id + " already exists");
        }
        storage.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T update(T entity) {
        ID id = idExtractor.getId(entity);
        if (!storage.containsKey(id)) {
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
        }
        storage.put(id, entity);
        return entity;
    }

    @Override
    public void deleteById(ID id) {
        if (storage.remove(id) == null) {
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
        }
    }
}
