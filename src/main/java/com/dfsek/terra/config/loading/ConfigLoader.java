package com.dfsek.terra.config.loading;

import com.dfsek.terra.config.ConfigTemplate;
import com.dfsek.terra.config.Configuration;
import com.dfsek.terra.config.annotations.Abstractable;
import com.dfsek.terra.config.annotations.Default;
import com.dfsek.terra.config.annotations.Value;
import com.dfsek.terra.config.loading.loaders.DoubleLoader;
import com.dfsek.terra.config.loading.loaders.IntLoader;
import com.dfsek.terra.config.loading.loaders.StringLoader;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to load a config using reflection magic.
 */
public class ConfigLoader {
    private final Map<Type, ClassLoader<?>> loaders = new HashMap<>();

    {
        registerLoader(String.class, new StringLoader());
        registerLoader(int.class, new IntLoader());
        registerLoader(Integer.class, new IntLoader());
        registerLoader(double.class, new DoubleLoader());
        registerLoader(Double.class, new DoubleLoader());
    }

    /**
     * Register a custom class loader for a type
     *
     * @param t      Type
     * @param loader Loader to load type with
     * @return This config loader
     */
    public ConfigLoader registerLoader(Type t, ClassLoader<?> loader) {
        loaders.put(t, loader);
        return this;
    }

    /**
     * Load a config from an InputStream and put it on a ConfigTemplate object.
     *
     * @param i      InputStream to load from
     * @param config ConfigTemplate object to put the config on
     * @throws IllegalAccessException If wacky reflection stuff occurs TODO: make dedicated exception
     */
    public void load(InputStream i, ConfigTemplate config) throws IllegalAccessException {
        Configuration configuration = new Configuration(i);
        for(Field field : config.getClass().getDeclaredFields()) {
            int m = field.getModifiers();
            if(Modifier.isFinal(m) || Modifier.isStatic(m)) continue; // Don't mess with static/final fields.
            if(!field.isAccessible()) field.setAccessible(true); // Make field accessible if it isn't.
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
