package handlers;

import http.HttpResponse;
import http.JsonHttpResponse;

import java.io.IOException;
import java.util.Locale;

public class AdminHandler extends Handler {
    @Override
    protected HttpResponse get() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected HttpResponse post() throws IOException {
        String action = getParameter("action");

        if (action == null) {
            return conflict("Missing action parameter");
        }

        switch (action.trim().toLowerCase(Locale.ROOT)) {
            case "reset":
                getStore().clear();
                return ok("Store reset");

            default:
                return conflict("Unknown action '" + action + "'");
        }
    }

    @Override
    protected HttpResponse put() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected HttpResponse delete() throws IOException {
        return error("Not implemented");
    }
}
