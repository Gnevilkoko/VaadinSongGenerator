package me.gnevilkoko.models.yaml;

import java.util.Map;

public class LanguageFileModel extends YamlModel {
    public LanguageFileModel(Map<String, Object> data) {
        super(data);
    }

    public LanguageFileModel(YamlModel yamlModel) {
        super(yamlModel);
    }

    public String getAppName(){
        return (String) getData().get(Names.APP_NAME.getKey());
    }

    public String getEnterNickname(){
        return (String) getData().get(Names.ENTER_NICKNAME.getKey());
    }

    public enum Names {
        APP_NAME("appname"),
        ENTER_NICKNAME("enter_nickname");

        private String key;

        Names(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return getKey();
        }
    }
}
