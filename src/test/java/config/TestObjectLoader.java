package config;

import com.dfsek.terra.config.loading.ClassLoader;

import java.util.Map;

public class TestObjectLoader implements ClassLoader<TestObject> {
    @SuppressWarnings("unchecked")
    @Override
    public TestObject load(Object c) {
        Map<String, Object> map = (Map<String, Object>) c;
        return new TestObject((String) map.get("string"), (Integer) map.get("number"));
    }
}
