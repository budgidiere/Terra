package com.dfsek.terra.config.loading;

import com.dfsek.terra.config.ConfigTemplate;
import com.dfsek.terra.config.Configuration;
import com.dfsek.terra.config.annotations.Abstractable;
import com.dfsek.terra.config.annotations.Default;
import com.dfsek.terra.config.annotations.Value;
import com.dfsek.terra.config.loading.loaders.StringLoader;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {
    private final Map<Type, ClassLoader<?>> loaders = new HashMap<>();

    {
        registerLoader(String.class, new StringLoader());
    }

    public ConfigLoader registerLoader(Type t, ClassLoader<?> loader) {
        loaders.put(t, loader);
        return this;
    }

    public void load(InputStream i, ConfigTemplate config) throws IllegalAccessException {
        Configuration configuration = new Configuration(i);
        for(Field field : config.getClass().getFields()) {
            boolean abstractable = false;
            boolean defaultable = false;
            Value value = null;
            System.out.println(field);
            for(Annotation annotation : field.getAnnotations()) {
                System.out.println(annotation);
                if(annotation instanceof Abstractable) abstractable = true;
                if(annotation instanceof Default) defaultable = true;
                if(annotation instanceof Value) value = (Value) annotation;
            }
            if(value != null) {
                System.out.println("Loading value...");
                Type type = value.type();
                Object o;
                if(configuration.contains(value.path())) {
                    if(loaders.containsKey(type))
                        o = loaders.get(type).load(configuration.get(value.path()));
                    else o = value.type().cast(configuration.get(value.path()));

                    field.set(config, o);

                    if(!value.type().isInstance(o))
                        throw new IllegalArgumentException(); // TODO: Replace with dedicated exception.
                } else if(!defaultable) throw new IllegalArgumentException();
            }
        }
    }
}
