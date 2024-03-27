package me.gnevilkoko.threads.StreamThread.models;

import com.google.gson.Gson;
import me.gnevilkoko.threads.StreamThread.models.WebSocketRequests.WebSocketRequestBase;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public abstract class WebSocketBase extends WebSocketListener {
    private Gson gson = new Gson();
    private OkHttpClient client = new OkHttpClient();
    private WebSocket webSocket;
    private String url;

    public OkHttpClient getClient() {
        return client;
    }

    public WebSocketBase(String url) {
        this.url = url;
        webSocket = connect(url);

    }

    public void sendMessage(WebSocketRequestBase message){
        webSocket.send(gson.toJson(message));
    }

    private WebSocket connect(String url){
        Request request = new Request.Builder().url(url).build();
        return client.newWebSocket(request, this);
    }

    protected WebSocket getWebSocket() {
        return webSocket;
    }

    public String getUrl() {
        return url;
    }

    public Gson getGson() {
        return gson;
    }
}
