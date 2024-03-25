package me.gnevilkoko.services;

import okhttp3.RequestBody;

import java.util.concurrent.CompletableFuture;

public interface HttpClientInterface {
    <T> CompletableFuture<T> get(String url, Class<T> clazz);
    <T> CompletableFuture<T> post(String url, RequestBody body, Class<T> responseClass);
}
