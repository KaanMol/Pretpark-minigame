package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import http.HttpMethod;
import http.HttpResponse;
import logging.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Handler implements HttpHandler {

    protected HttpMethod method;
    protected Map<String, String> params;
    protected HttpExchange exchange;

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

            reply(response.code(), response.body());
        } catch (Exception ex) {
            Logger.warn(ex, "Exception while handling request");
            reply(500, ex.getMessage());
        }
    }

    abstract protected HttpResponse get() throws IOException;
    abstract protected HttpResponse post() throws IOException

    abstract protected HttpResponse put() throws IOException;

    abstract protected HttpResponse delete() throws IOExcepti

    
    protected HttpResponse ok(String body) {
        return new HttpResponse(200, body);
    }
    p

        return new HttpResponse(400, body);
    }
    p

        return new HttpResponse(500, body);
    }
    p

        exchange.sendResponseHeaders(status, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }

    protected String getParameter(String name) {
        return params.get(name);
    }

    private Map<String, String> extractQuery(URI url) {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8), URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }
                    
        return query_pairs;
    }
}
