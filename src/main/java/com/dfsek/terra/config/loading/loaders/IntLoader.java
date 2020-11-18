package com.dfsek.terra.config.loading.loaders;

import com.dfsek.terra.config.loading.ClassLoader;

/**
 * Default loader for Integer types.
 */
public class IntLoader implements ClassLoader<Integer> {
    @Override
    public Integer load(Object c) {
        return Integer.parseInt(c.toString());
    }
}
