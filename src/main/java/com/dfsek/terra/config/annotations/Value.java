package com.dfsek.terra.config.annotations;

public @interface Value {
    Class<?> type();

    String path();
}
