package database;

import domain.Card;
import domain.Win;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WinStore {
    private final List<Win> wins;

    public WinStore() {
        this.wins = new ArrayList<>();
    }

    public Win create(String gameId, Card card, LocalDateTime timestamp, int points) {
        Win win = new Win(gameId, card.cardId(), timestamp, points);
        wins.add(win);
        return win;
    }

    public List<Win> findByCard(Card card) {
        List<Win> result = new ArrayList<>();
        for (Win win : wins) {
            if (win.cardId().equals(card.cardId())) {
                result.add(win);
            }
        }
        return result;
    }
}
