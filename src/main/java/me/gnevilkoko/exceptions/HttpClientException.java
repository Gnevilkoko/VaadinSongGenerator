package me.gnevilkoko.exceptions;

import okhttp3.Response;

public class HttpClientException extends Exception {
    private Response response;

    public HttpClientException(Response response) {
        super();
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
