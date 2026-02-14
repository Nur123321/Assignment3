package com.assignment3.repository.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
    private static volatile CacheManager instance;
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    public <T> Optional<T> get(String key, Class<T> type) {
        Object value = cache.get(key);
        if (value == null || !type.isInstance(value)) {
            return Optional.empty();
        }
        return Optional.of(type.cast(value));
    }

    public <T> Optional<List<T>> getList(String key, Class<T> elementType) {
        Object value = cache.get(key);
        if (!(value instanceof List<?> listValue)) {
            return Optional.empty();
        }

        List<T> typedValues = new ArrayList<>();
        for (Object item : listValue) {
            if (!elementType.isInstance(item)) {
                return Optional.empty();
            }
            typedValues.add(elementType.cast(item));
        }
        return Optional.of(typedValues);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public void evict(String key) {
        cache.remove(key);
    }

    public void evictByPrefix(String keyPrefix) {
        cache.keySet().removeIf(key -> key.startsWith(keyPrefix));
    }

    public void clear() {
        cache.clear();
    }
}
