package com.dfsek.terra.config.loading;

/**
 * Loads a class from an Object retrieved from a config.
 *
 * @param <T>
 */
public interface ClassLoader<T> {
    T load(Object c);
}
