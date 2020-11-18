package com.dfsek.terra.config.loading;

public interface ClassLoader<T> {
    T load(Object c);
}
