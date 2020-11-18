package config;

import com.dfsek.terra.config.loading.ConfigLoader;
import org.junit.jupiter.api.Test;

public class ConfigTest {
    @Test
    public void config() throws IllegalAccessException {
        ConfigLoader loader = new ConfigLoader();
        ExampleConfig example = new ExampleConfig();
        loader.load(this.getClass().getResourceAsStream("/test.yml"), example);
        System.out.println(example.getValue());
        System.out.println(example.getVal1());
        System.out.println(example.getA());
        System.out.println(example.getNumber());
    }
}
