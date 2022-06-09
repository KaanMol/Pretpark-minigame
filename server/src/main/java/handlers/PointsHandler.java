package handlers;

import domain.Account;
import domain.Card;
import domain.Win;
import http.JsonHttpResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PointsHandler extends Handler {
    @Override
    protected JsonHttpResponse get() {
        String accountId = getParameter("accountId");
        String nfcId = getParameter("nfcId");

        if (accountId != null) {
            return getByAccountId(accountId);
        } else if (nfcId != null) {
            return getByNfcId(nfcId);
        } else {
            return conflict("Missing parameter; accountId or nfcId");
        }
    }

    private JsonHttpResponse getByAccountId(String accountId) {
        Account account = getStore().accounts().find(accountId);

        if (account == null) {
            return conflict("Account not registered");
        }

        List<Card> cards = getStore().cards().findByAccount(account);
        List<Win> wins = new ArrayList<>();

        for (Card card : cards) {
            wins.addAll(getStore().wins().findByCard(card));
        }

        return ok(wins);
    }

    private JsonHttpResponse getByNfcId(String nfcId) {
        Card card = getStore().cards().find(nfcId);

        if (card == null) {
            return conflict("Card not registered to an account");
        }

        List<Win> wins = getStore().wins().findByCard(card);

        return ok(wins);
    }

    @Override
    protected JsonHttpResponse post() {
        String nfcId = getParameter("nfcId");
        String gameId = getParameter("gameId");

        if (nfcId == null) {
            return conflict("Missing parameter 'nfcId'");
        }

        if (gameId == null) {
            return conflict("Missing parameter 'gameId'");
        }

        Card card = getStore().cards().find(nfcId);

        if (card == null) {
            return conflict("Card not registered to an account");
        }

        // todo: algorithm to calculate points
        int points = 10;

        getStore().wins().create(gameId, card, LocalDateTime.now(), points);

        return ok("Points added");
    }

    @Override
    protected JsonHttpResponse put() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected JsonHttpResponse delete() throws IOException {
        return error("Not implemented");
    }
}