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
        String nfcId = getParameter("nfcId");
        String accountId = getParameter("accountId");

        if (nfcId != null) {
            return getCardById(nfcId);
        } else if (accountId != null) {
            return getCardsByAccount(accountId);
        } else {
            return conflict("Missing parameter; accountId or nfcId");
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

    private HttpResponse getCardById(String nfcId) throws IOException {
        Card card = getStore().cards().find(nfcId);

        if (card == null) {
            return conflict("Card not registered to an account");
        }

        return ok(card);
    }

    @Override
    protected HttpResponse post() throws IOException {
        String accountId = getParameter("accountId");
        String cardNumber = getParameter("cardNumber");
        String nfcId = getStore().nfcs().getNfcId(cardNumber);

        if(cardNumber == null) {
            return conflict("Missing parameter 'cardNumber'");
        }

        if(accountId == null) {
            return conflict("Missing parameter 'accountId'");
        }

        if(nfcId == null) {
            return conflict("Could not find NFC match for cardNumber '" + cardNumber + "'");
        }

        if(getStore().cards().find(nfcId) != null) {
            return conflict("Card already registered");
        }

        Account account = getStore().accounts().findOrCreate(accountId);
        Card card = getStore().cards().create(nfcId, account);

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
