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
    public <T> CompletableFuture<T> get(String url, Class<T> clazz) {
        CompletableFuture<T> future = new CompletableFuture<>();

        client.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
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

    @NotNull
    @Override
    public <T> CompletableFuture<T> post(String url, RequestBody body, Class<T> clazz) {
        System.out.println("POST");

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        CompletableFuture<T> future = new CompletableFuture<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("FAIL");
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("OKAY");
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("PELEP");
                    String json = response.body().string();
                    T object = gson.fromJson(json, clazz);
                    future.complete(object);
                } else {
                    System.out.println("KABOm");
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
}
