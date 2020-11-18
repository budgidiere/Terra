package config;

import com.dfsek.terra.config.ConfigTemplate;
import com.dfsek.terra.config.annotations.Default;
import com.dfsek.terra.config.annotations.Value;

public class ExampleConfig implements ConfigTemplate {
    @Default
    @Value(type = String.class, path = "value2")
    private final String val1 = "test value";
    @Value(type = String.class, path = "value1")
    private String value;
    @Value(type = String.class, path = "a.b.c.d.e.f")
    private String a;

    public String getValue() {
        return value;
    }

    public String getA() {
        return a;
    }

    public String getVal1() {
        return val1;
    }
}
