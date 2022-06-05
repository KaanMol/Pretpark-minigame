package database;

import domain.Card;
import domain.Win;
import logging.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WinStore {
    private final List<Win> wins;

    public WinStore() {
        this.wins = new ArrayList<>();
    }

    public Win create(String gameId, Card card, LocalDateTime timestamp, int points) {
        Logger.debug("Creating win for nfcId " + card.nfcId() + " with gameId " + gameId);

        Win win = new Win(gameId, card.nfcId(), timestamp.toString(), points);
        wins.add(win);
        return win;
    }

    public List<Win> findByCard(Card card) {
        List<Win> result = new ArrayList<>();
        for (Win win : wins) {
            if (win.nfcId().equals(card.nfcId())) {
                result.add(win);
            }
        }
        return result;
    }

    public List<Win> all() {
        return wins;
    }

    public void set(List<Win> wins) {
        clear();
        this.wins.addAll(wins);
    }

    public void clear() {
        this.wins.clear();
    }
}
