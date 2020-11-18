package com.dfsek.terra.config.loading.loaders;

import com.dfsek.terra.config.loading.ClassLoader;

/**
 * Default loader for String type.
 */
public class StringLoader implements ClassLoader<String> {
    @Override
    public String load(Object c) {
        return c.toString();
    }
}
