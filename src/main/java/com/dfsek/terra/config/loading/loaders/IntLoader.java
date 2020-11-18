package com.dfsek.terra.config.loading.loaders;

import com.dfsek.terra.config.loading.ClassLoader;

public class IntLoader implements ClassLoader<Integer> {
    @Override
    public Integer load(Object c) {
        return Integer.parseInt(c.toString());
    }
}
