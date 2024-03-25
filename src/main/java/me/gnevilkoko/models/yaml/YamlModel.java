package me.gnevilkoko.models.yaml;

import me.gnevilkoko.models.ModelBase;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

    public void save(String fileName){
        try {
            DumperOptions options = new DumperOptions();
            options.setExplicitStart(false);
            options.setCanonical(false);
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

            PrintWriter writer = new PrintWriter(System.getProperty("user.dir")+ File.separator+fileName);
            Yaml yaml = new Yaml(options);
            yaml.dump(getData(), writer);
        } catch (FileNotFoundException ignored) {}
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
