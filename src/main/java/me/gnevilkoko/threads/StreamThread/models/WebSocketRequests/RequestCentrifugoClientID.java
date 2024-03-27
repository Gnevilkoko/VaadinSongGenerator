package me.gnevilkoko.threads.StreamThread.models.WebSocketRequests;

import java.util.HashMap;
import java.util.Map;

public class RequestCentrifugoClientID extends WebSocketRequestBase {
    private Map<String, String> params;
    private int id;

    public RequestCentrifugoClientID(String socketConnectionToken) {
        this.params = new HashMap<>();
        params.put("token", socketConnectionToken);

        this.id = 1;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
