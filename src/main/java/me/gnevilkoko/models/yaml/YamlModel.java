package me.gnevilkoko.models.yaml;

import me.gnevilkoko.models.ModelBase;

import java.util.Map;

public class YamlModel extends ModelBase {
    private final Map<String, Object> data;

    public YamlModel(Map<String, Object> data) {
        this.data = data;
    }
    public YamlModel(YamlModel yamlModel){
        this.data = yamlModel.getData();
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
