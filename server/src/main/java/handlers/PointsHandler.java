package handlers;

import domain.Account;
import domain.Card;
import domain.Win;
import http.HttpResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PointsHandler extends Handler{
    @Override
    protected HttpResponse get() {
        String accountId = getParameter("accountId");
        String cardId = getParameter("cardId");

        if (accountId != null) {
            return getByAccountId(accountId);
        } else if (cardId != null) {
            return getByCardId(cardId);
        } else {
            return error("Missing parameters accountId or cardId");
        }
    }

    private HttpResponse getByAccountId(String accountId) {
        Account account = getStore().accounts().findOrCreate(accountId);
        List<Card> cards = getStore().cards().findByAccount(account);
        List<Win> wins = new ArrayList<>();

        int totalPoints = 0;

        for (Card card : cards) {
            for(Win win : getStore().wins().findByCard(card)) {
                wins.add(win);
                totalPoints += win.points();
            }
        }

        return ok(new PointsResult(wins, totalPoints));
    }

    private HttpResponse getByCardId(String cardId) {
        Card card = getStore().cards().find(cardId);

        if (card == null) {
            return conflict("Card not registered");
        }

        List<Win> wins = getStore().wins().findByCard(card);

        int totalPoints = 0;

        for (Win win : wins) {
            totalPoints += win.points();
        }

        return ok(new PointsResult(wins, totalPoints));
    }

    @Override
    protected HttpResponse post() {
        String cardId = getParameter("cardId");
        String gameId = getParameter("gameId");

        Card card = getStore().cards().find(cardId);

        if (card == null) {
            return conflict("Card not registered");
        }

        // todo: algorithm to calculate points
        int points = 10;

        getStore().wins().create(gameId, card, LocalDateTime.now(), points);

        return ok("Points added");
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

record PointsResult(List<Win> wins, int totalPoints) {

}
