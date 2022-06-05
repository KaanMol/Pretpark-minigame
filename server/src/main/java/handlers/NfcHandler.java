package handlers;

import http.HttpResponse;

import java.io.IOException;

public class NfcHandler extends Handler{
    @Override
    protected HttpResponse get() throws IOException {
        String nfcId = getParameter("nfcId");
        String cardNumber = getParameter("cardNumber");

        if (nfcId == null && cardNumber == null) {
            return conflict("Missing parameter; nfcId or cardNumber");
        }

        if (nfcId != null) {
            return getCardNumber(nfcId);
        } else {
            return getNfcId(cardNumber);
        }
    }

    private HttpResponse getCardNumber(String nfcId) {
        String cardNumber = getStore().nfcs().getcardNumber(nfcId);

        if (cardNumber == null) {
            return conflict("No cardNumber found for nfcId: " + nfcId);
        }

        return ok(cardNumber);
    }

    private HttpResponse getNfcId(String cardNumber) {
        String nfcId = getStore().nfcs().getNfcId(cardNumber);

        if (nfcId == null) {
            return conflict("No nfcId found for cardNumber: " + cardNumber);
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
