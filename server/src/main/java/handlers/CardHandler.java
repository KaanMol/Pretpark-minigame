package handlers;

import http.HttpResponse;

import java.io.IOException;

public class CardHandler extends Handler{
    @Override
    protected HttpResponse get() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected HttpResponse post() throws IOException {
        return error("Not implemented");
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
