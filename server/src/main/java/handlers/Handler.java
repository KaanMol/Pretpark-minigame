package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.Store;
import http.HttpMethod;
import http.HttpResponse;
import http.JsonHttpResponse;
import http.StreamHttpResponse;
import logging.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Handler implements HttpHandler {

    protected HttpMethod method;
    protected Map<String, String> params;
    protected HttpExchange exchange;

    private Store store;

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        method = HttpMethod.valueOf(exchange.getRequestMethod());

        Logger.debug("Incoming " + method + " " + exchange.getRequestURI());

        params = extractQuery(exchange.getRequestURI());
        this.exchange = exchange;

        try {
            String handler = getClass().getSimpleName();
            Logger.debug("Handling request with " + handler);

            HttpResponse response = switch (method) {
                case POST -> post();
                case PUT -> put();
                case DELETE -> delete();
                default -> get();
            };

            if (response instanceof JsonHttpResponse jsonResponse) {
                replyJson(jsonResponse.code(), jsonResponse.json());
            } else if (response instanceof StreamHttpResponse streamResponse) {
                replyStream(streamResponse.mime(), streamResponse.bytes());
            }
        } catch (Exception ex) {
            Logger.warn(ex, "Exception while handling request");
            replyJson(500, ex.getMessage());
        }
    }

    abstract protected HttpResponse get() throws IOException;

    abstract protected HttpResponse post() throws IOException;

    abstract protected HttpResponse put() throws IOException;

    abstract protected HttpResponse delete() throws IOException;

    protected Store getStore() {
        return store;
    }

    protected String getParameter(String name) {
        return params.get(name);
    }

    protected JsonHttpResponse ok(String content) {
        return ok(new Message(content));
    }

    protected JsonHttpResponse ok(Object object) {
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            return new JsonHttpResponse(200, json);
        } catch (Exception ex) {
            Logger.warn(ex, "Exception while serializing object");
            return new JsonHttpResponse(500, ex.getMessage());
        }
    }

    protected JsonHttpResponse conflict(String error) {
        return conflict(new Error(error));
    }

    protected JsonHttpResponse conflict(Object object) {
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            return new JsonHttpResponse(400, json);
        } catch (Exception ex) {
            Logger.warn(ex, "Exception while serializing object");
            return new JsonHttpResponse(500, ex.getMessage());
        }
    }

    protected JsonHttpResponse error(String error) {
        return error(new Error(error));
    }

    protected JsonHttpResponse error(Object object) {
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            return new JsonHttpResponse(500, json);
        } catch (Exception ex) {
            Logger.warn(ex, "Exception while serializing object");
            return new JsonHttpResponse(500, ex.getMessage());
        }
    }

    private void replyJson(int status, String json) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, json.length());
        exchange.getResponseBody().write(json.getBytes());
        exchange.getResponseBody().close();
    }

    private void replyStream(String mime, byte[] bytes) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", mime);
        exchange.sendResponseHeaders(200, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.getResponseBody().close();
    }

    private Map<String, String> extractQuery(URI url) {

        if(url.getQuery() == null) {
            return new HashMap<>();
        }

        Map<String, String> query_pairs = new LinkedHashMap<>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8),
                    URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }
        return query_pairs;
    }
}

record Error(String error) {
}

record Message(String message) {
}
