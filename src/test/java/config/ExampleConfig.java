package config;

import com.dfsek.terra.config.ConfigTemplate;
import com.dfsek.terra.config.annotations.Default;
import com.dfsek.terra.config.annotations.Value;

public class ExampleConfig implements ConfigTemplate {
    @Value(type = String.class, path = "value1")
    public String value;

    @Default
    @Value(type = String.class, path = "value2")
    public String val1 = "test value";

    @Value(type = String.class, path = "a.b.c.d.e.f")
    public String a;
}
