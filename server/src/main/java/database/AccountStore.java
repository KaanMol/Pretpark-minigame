package database;

import domain.Account;
import logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class AccountStore {
    private final List<Account> accounts;

    public AccountStore() {
        accounts = new ArrayList<>();
    }

    public Account create(String id) {
        Account account = find(id);

        if (account != null) {
            return account;
        }

        Logger.debug("Creating account with id: " + id);

        account = new Account(id);
        accounts.add(account);
        return account;
    }

    public void delete(String id) {
        Logger.debug("Deleting account with id: " + id);

        Account account = find(id);
        if (account != null) {
            accounts.remove(account);
        }
    }

    public Account findOrCreate(String id) {
        Account account = find(id);
        if (account == null) {
            account = create(id);
        }
        return account;
    }

    public Account find(String id) {
        for (Account account : accounts) {
            if (account.accountId().equals(id)) {
                return account;
            }
        }
        return null;
    }
}
