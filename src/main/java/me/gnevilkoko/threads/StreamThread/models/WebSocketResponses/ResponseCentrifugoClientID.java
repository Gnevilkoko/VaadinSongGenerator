package me.gnevilkoko.threads.StreamThread.models.WebSocketResponses;

import java.util.Map;

public class ResponseCentrifugoClientID extends WebSocketResponseBase{
    private long id;
    private Map<String, String> result;

    public ResponseCentrifugoClientID(int id, Map<String, String> result) {
        this.id = id;
        this.result = result;
    }

    public ResponseCentrifugoClientID() {
    }

    public String getClientID(){
        return result.get("client");
    }

    public String getVersion(){
        return result.get("version");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
