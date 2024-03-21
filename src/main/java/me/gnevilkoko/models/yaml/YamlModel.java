package me.gnevilkoko.models.yaml;

import me.gnevilkoko.models.ModelBase;

import java.util.Map;

public class YamlModel extends ModelBase {
    private final Map<String, Object> data;

    public YamlModel(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
