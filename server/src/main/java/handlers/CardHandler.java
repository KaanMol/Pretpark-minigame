package handlers;

import domain.Account;
import domain.Card;
import http.HttpResponse;

import java.io.IOException;

public class CardHandler extends Handler {
    @Override
    protected HttpResponse get() throws IOException {
        String cardId = getParameter("cardId");

        Card card = getStore().cards().find(cardId);

        if (card == null) {
            return conflict("Card not registered");
        }

        return ok(card);
    }

    @Override
    protected HttpResponse post() throws IOException {
        String accountId = getParameter("accountId");
        String cardId = getParameter("cardId");

        if(getStore().cards().find(cardId) != null) {
            return conflict("Card already registered");
        }

        Account account = getStore().accounts().findOrCreate(accountId);
        Card card = getStore().cards().create(cardId, account);

        return ok(card);
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
