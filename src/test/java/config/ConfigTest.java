package config;

import com.dfsek.terra.config.loading.ConfigLoader;
import org.junit.jupiter.api.Test;

public class ConfigTest {
    @Test
    public void config() throws IllegalAccessException {
        ConfigLoader loader = new ConfigLoader();
        loader.registerLoader(TestObject.class, new TestObjectLoader());
        ExampleConfig example = new ExampleConfig();
        loader.load(this.getClass().getResourceAsStream("/test.yml"), example);
        System.out.println(example.getString1());
        System.out.println(example.getString());
        System.out.println(example.getNestedString());
        System.out.println(example.getNumber());
        System.out.println(example.getTestObject());
    }
}
