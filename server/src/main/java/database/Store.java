package database;

public class Store {
    private final AccountStore accounts;
    private final CardStore cards;
    private final WinStore wins;

    public Store() {
        accounts = new AccountStore();
        cards = new CardStore();
        wins = new WinStore();
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

    public void save() {
        // todo: save to file?
    }

    public void load() {
        // todo: load from file?
    }
}