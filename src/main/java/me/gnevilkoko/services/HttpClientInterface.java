package me.gnevilkoko.services;

import okhttp3.RequestBody;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface HttpClientInterface {
    <T> CompletableFuture<T> get(String url, Class<T> clazz, Map<String, String> headers);
    <T> CompletableFuture<T> post(String url, RequestBody body, Map<String, String> headers, Class<T> responseClass);
}
