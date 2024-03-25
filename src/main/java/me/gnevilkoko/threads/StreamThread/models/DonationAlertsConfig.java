package me.gnevilkoko.threads.StreamThread.models;

import me.gnevilkoko.models.yaml.YamlModel;

import java.util.Map;

public class DonationAlertsConfig extends YamlModel {

    public DonationAlertsConfig(Map<String, Object> data) {
        super(data);
    }

    public DonationAlertsConfig(YamlModel yamlModel) {
        super(yamlModel);
    }

    public String getClientId(){
        return (String) getData().get(Names.CLIENT_ID.getKey());
    }

    public String getRedirectUri(){
        return (String) getData().get(Names.REDIRECT_URI.getKey());
    }
    public String getClientSecret(){
        return (String) getData().get(Names.CLIENT_SECRET.getKey());
    }
    public String getCode(){
        return (String) getData().get(Names.CODE.getKey());
    }

    public enum Names {
        CLIENT_ID("client_id"),
        CLIENT_SECRET("client_secret"),
        REDIRECT_URI("redirect_uri"),
        CODE("code");
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
