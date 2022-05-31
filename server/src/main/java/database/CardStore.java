package database;

import domain.Account;
import domain.Card;
import logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class CardStore {
    private final List<Card> cards;

    public CardStore() {
        cards = new ArrayList<>();
    }

    public Card create(String id, Account account) {
        Card card = find(id);
        if (card != null) {
            return card;
        }

        Logger.debug("Creating card with id " + id);

        card = new Card(id, account.accountId());
        cards.add(card);
        return card;
    }

    public Card find(String id) {
        for (Card card : cards) {
            if (card.cardId().equals(id)) {
                return card;
            }
        }
        return null;
    }

    public List<Card> findByAccount(Account account) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.accountId().equals(account.accountId())) {
                result.add(card);
            }
        }
        return result;
    }

    public void delete(String id) {
        for (Card card : cards) {
            if (card.cardId().equals(id)) {
                Logger.debug("Deleting card with id " + id);

                cards.remove(card);
                return;
            }
        }
    }

    public List<Card> all() {
        return cards;
    }

    public void set(List<Card> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
    }
}
