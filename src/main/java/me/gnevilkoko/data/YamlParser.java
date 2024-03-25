package me.gnevilkoko.data;

import me.gnevilkoko.models.yaml.YamlModel;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class YamlParser implements Parser{
    @Override
    public YamlModel parse(Reader reader) {
        String yamlContent = reader.read();
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(yamlContent);
        return new YamlModel(data);
    }
}
