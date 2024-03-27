package me.gnevilkoko.threads.StreamThread.models;

import me.gnevilkoko.exceptions.NotEnoughKeyPairsException;
import me.gnevilkoko.models.yaml.YamlModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DonationAlertsAccesTokenConfig extends YamlModel {
    public DonationAlertsAccesTokenConfig(Map<String, Object> data) {
        super(data);
    }

    public DonationAlertsAccesTokenConfig(YamlModel yamlModel) {
        super(yamlModel);
    }

    public DonationAlertsAccesTokenConfig(String source) throws NotEnoughKeyPairsException {
        super(new HashMap<>());

        parse(source);
    }

    private void parse(String source) throws NotEnoughKeyPairsException {
        Map<String, Object> data = getData();

        String cuttedToSharp = source.substring(source.lastIndexOf("#")+1);
        String[] parameters = cuttedToSharp.split("&");

        ArrayList<String> usedKeys = new ArrayList<>();
        for(String parameter : parameters){
            String[] parts = parameter.split("=");
            String key = parts[0];
            String value = parts[1];

            usedKeys.add(key);

            data.put(key, value);
        }

        ArrayList<String> requiredMoreKeys = new ArrayList<>();
        for(Names requiredName : Names.values()){
            String requiredKey = requiredName.getKey();

            if(!usedKeys.contains(requiredKey)){
                requiredMoreKeys.add(requiredKey);
            }
        }

        if(requiredMoreKeys.size() > 0){
            throw new NotEnoughKeyPairsException(requiredMoreKeys);
        }
    }

    public String getTokenType(){
        return (String) getData().get(Names.TOKEN_TYPE.getKey());
    }

    public String getAccessToken(){
        return (String) getData().get(Names.ACCESS_TOKEN.getKey());
    }

    public long getExpiresIn(){
        return (long) getData().get(Names.EXPIRES_IN.getKey());
    }

    public String getFullToken(){
        return getTokenType()+" "+getAccessToken();
    }

    public String getSocketConnectionToken(){
        return (String) getData().get("socket_connection_token");
    }
    public long getUserID(){
        return (long) getData().get("user_id");
    }

    public enum Names {
        TOKEN_TYPE("token_type"),
        ACCESS_TOKEN("access_token"),
        EXPIRES_IN("expires_in");
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
