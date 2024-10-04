package com.pcverksted.inventory.hardware;
import java.util.HashMap;
import java.util.Map;

public class CustomProperties {
    private Map<String, Object> properties;

    public CustomProperties() {
        properties = new HashMap<>();
    }

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public Map<String, Object> getAllProperties() {
        return properties;
    }
}