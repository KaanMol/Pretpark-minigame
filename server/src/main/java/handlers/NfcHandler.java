package handlers;

import http.HttpResponse;

import java.io.IOException;

public class NfcHandler extends Handler{
    @Override
    protected HttpResponse get() throws IOException {
        String nfcId = getParameter("nfcId");
        String readableId = getParameter("readableId");

        if (nfcId == null && readableId == null) {
            return error("Missing parameter; nfcId or readableId");
        }

        if (nfcId != null) {
            return getReadableId(nfcId);
        } else {
            return getNfcId(readableId);
        }
    }

    private HttpResponse getReadableId(String nfcId) {
        String readableId = getStore().nfcs().getReadableId(nfcId);

        if (readableId == null) {
            return error("No readable id found for nfcId: " + nfcId);
        }

        return ok(readableId);
    }

    private HttpResponse getNfcId(String readableId) {
        String nfcId = getStore().nfcs().getNfcId(readableId);

        if (nfcId == null) {
            return error("No nfcId found for readableId: " + readableId);
        }

        return ok(nfcId);
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
