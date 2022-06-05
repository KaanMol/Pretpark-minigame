package handlers;

import domain.Account;
import domain.Card;
import http.HttpResponse;
import http.JsonHttpResponse;

import java.io.IOException;
import java.util.List;

public class CardHandler extends Handler {
    @Override
    protected HttpResponse get() throws IOException {
        String cardId = getParameter("cardId");
        String accountId = getParameter("accountId");

        if (cardId != null) {
            return getCardById(cardId);
        } else if (accountId != null) {
            return getCardsByAccount(accountId);
        } else {
            return error("Missing parameter; accountId or cardId");
        }
    }

    private HttpResponse getCardsByAccount(String accountId) throws IOException {
        Account account = getStore().accounts().find(accountId);

        if(account == null) {
            return conflict("Account not registered");
        }

        List<Card> cards = getStore().cards().findByAccount(account);

        return ok(cards);
    }

    private HttpResponse getCardById(String cardId) throws IOException {
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
