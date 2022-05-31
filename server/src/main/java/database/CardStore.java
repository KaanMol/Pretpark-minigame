package database;

import domain.Account;
import domain.Card;

import java.util.ArrayList;
import java.util.List;

public class CardStore {
    private final List<Card> cards;

    public CardStore() {
        cards = new ArrayList<>();
    }

    public Card create(String id, Account account) {
        Card card = new Card(id, account.accountId());
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
                cards.remove(card);
                return;
            }
        }
    }
}
