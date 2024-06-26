package me.gnevilkoko.services;

import com.google.gson.Gson;
import me.gnevilkoko.exceptions.HttpClientException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HttpClient implements HttpClientInterface {
    private OkHttpClient client;
    private Gson gson;

    public HttpClient(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @NotNull
    @Override
    public <T> CompletableFuture<T> get(String url, Class<T> clazz, Map<String, String> headers) {
        CompletableFuture<T> future = new CompletableFuture<>();

        Request.Builder builder = new Request.Builder().url(url);
        for (String key : headers.keySet()) {
            builder.addHeader(key, headers.get(key));
        }

        client.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    System.out.println(json);
                    T object = gson.fromJson(json, clazz);
                    future.complete(object);
                } else {
                    future.completeExceptionally(new HttpClientException(response));
                }
            }
        });

        return future;
    }

    @NotNull
    @Override
    public <T> CompletableFuture<T> post(String url, RequestBody body, Map<String, String> headers, Class<T> clazz) {
        CompletableFuture<T> future = new CompletableFuture<>();

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body);

        for (String key : headers.keySet()) {
            builder.addHeader(key, headers.get(key));
        }

        client.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    T object = gson.fromJson(json, clazz);
                    future.complete(object);
                } else {
                    future.completeExceptionally(new HttpClientException(response));
                }
            }
        });

        return future;
    }

    public String addParameters(String link, Map<String, String> params){
        StringBuilder sb = new StringBuilder(link);
        sb.append("?");

        String[] keys = params.keySet().toArray(new String[0]);

        for(int i = 0; i < params.keySet().size(); i++){
            String key = keys[i];
            sb.append(key).append("=").append(params.get(key));

            if(i < params.size()-1)
                sb.append("&");
        }


        return sb.toString();
    }

    public Gson getGson() {
        return gson;
    }
}
