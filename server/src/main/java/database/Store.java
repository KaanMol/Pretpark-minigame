package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Account;
import domain.Card;
import domain.Product;
import domain.Win;
import io.FileManager;
import logging.Logger;

import java.util.List;

public class Store {
    private final AccountStore accounts;
    private final CardStore cards;
    private final WinStore wins;

    private final LocalisationStore localisations;

    private final ProductStore products;

    public Store() {
        accounts = new AccountStore();
        cards = new CardStore();
        wins = new WinStore();
        products = new ProductStore();
        localisations = new LocalisationStore();
    }

    public AccountStore accounts() {
        return accounts;
    }

    public CardStore cards() {
        return cards;
    }

    public WinStore wins() {
        return wins;
    }

    public ProductStore products() {
        return products;
    }

    public LocalisationStore localisations() {
        return localisations;
    }

    public void save() {
        try {
            StoreState state = new StoreState(accounts.all(), cards.all(), wins.all(), products.all());
            String json = new ObjectMapper().writeValueAsString(state);
            FileManager.write("db.json", json);
        } catch (Exception ex) {
            Logger.warn(ex, "Could not save store state");
        }
    }

    public void load() {
        try {
            String json = FileManager.read("db.json");
            StoreState state = new ObjectMapper().readValue(json, StoreState.class);
            accounts.set(state.accounts());
            cards.set(state.cards());
            wins.set(state.wins());
        } catch (Exception ex) {
            Logger.warn(ex, "Could not load store state");
        }
    }

    public void clear() {
        accounts.clear();
        cards.clear();
        wins.clear();
        products.clear();
    }
}

record StoreState(List<Account> accounts, List<Card> cards, List<Win> wins, List<Product> products) {
}