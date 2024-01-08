package com.boar.lil.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.boar.lil.sdk.dto.CreditCard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class CreditCardSdk {

    private final HttpClient httpClient;
    private final String baseUrl;

    public CreditCardSdk(String baseUrl) {
        this.httpClient = HttpClients.createDefault();
        this.baseUrl = baseUrl;
    }

    public CompletableFuture<Void> addCreditCard(CreditCard creditcard) {
        if (creditcard == null) {
            throw new IllegalArgumentException("creditcard cannot be null");
        }

        String url = baseUrl + "api/creditcards";
        List<HttpHeader> headers = new ArrayList<>();
        headers.add(new HttpHeader("isJavanation", "true"));

        return postAsync(url, creditcard, headers);
    }

    private CompletableFuture<Void> postAsync(String url, Object data, List<HttpHeader> headers) {
        var future = new CompletableFuture<Void>();

        try {
            HttpPost request = new HttpPost(url);
            headers.forEach(header -> request.setHeader(header.getName(), header.getValue()));

            // object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(data);

            StringEntity entity = new StringEntity(jsonData);
            request.setEntity(entity);

            httpClient.execute(request, response -> {
                var statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 300) {
                    future.complete(null);
                }
                else
                {
                    future.completeExceptionally(new RuntimeException("HTTP error code: " + statusCode));
                }
                return null;
            });
        } catch (Exception e) {
            future.completeExceptionally(e);
        }

        return future;
    }

    // Define a simple representation of HTTP headers
    private static class HttpHeader {
        private final String name;
        private final String value;

        public HttpHeader(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}