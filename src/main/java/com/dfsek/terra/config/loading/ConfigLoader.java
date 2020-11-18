package com.dfsek.terra.config.loading;

import com.dfsek.terra.config.ConfigTemplate;
import com.dfsek.terra.config.annotations.Abstractable;
import com.dfsek.terra.config.annotations.Default;
import com.dfsek.terra.config.annotations.Value;
import com.dfsek.terra.config.loading.loaders.StringLoader;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
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

    public void load(InputStream i, ConfigTemplate config) throws IOException, InvalidConfigurationException, IllegalAccessException {
        String is = IOUtils.toString(i);
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.load(is);
        for(Field field : config.getClass().getFields()) {
            boolean abstractable = false;
            boolean defaultable = false;
            Value value = null;
            for(Annotation annotation : field.getAnnotations()) {
                if(annotation instanceof Abstractable) abstractable = true;
                if(annotation instanceof Default) defaultable = true;
                if(annotation instanceof Value) value = (Value) annotation;
            }
            if(value != null) {
                Type type = value.type();
                Object o;
                if(configuration.contains(value.path())) {
                    if(loaders.containsKey(type))
                        o = loaders.get(type).load(configuration.getConfigurationSection(value.path()));
                    else o = value.type().cast(configuration.get(value.path()));

                    field.set(config, o);

                    if(!value.type().isInstance(o))
                        throw new IllegalArgumentException(); // TODO: Replace with dedicated exception.
                } else if(!defaultable) throw new InvalidConfigurationException();
            }
        }
    }
}
