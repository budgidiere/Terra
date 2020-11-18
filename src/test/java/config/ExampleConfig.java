package config;

import com.dfsek.terra.config.ConfigTemplate;
import com.dfsek.terra.config.annotations.Default;
import com.dfsek.terra.config.annotations.Value;

public class ExampleConfig implements ConfigTemplate {
    @Default
    @Value(path = "value2")
    private final String string = "test value";

    @Value(path = "value1")
    private String string1;

    @Value(path = "a.b.c.d.e.f")
    private String nestedString;

    @Value(path = "number")
    private int number;

    @Value(path = "object")
    private TestObject testObject;

    public String getString1() {
        return string1;
    }

    public String getNestedString() {
        return nestedString;
    }

    public String getString() {
        return string;
    }

    public int getNumber() {
        return number;
    }

    public TestObject getTestObject() {
        return testObject;
    }
}
