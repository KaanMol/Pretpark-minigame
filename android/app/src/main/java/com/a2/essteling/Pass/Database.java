package com.a2.essteling.Pass;

import java.util.HashMap;

public class Database {

    //todo: use local storage / cache?

    private static final HashMap<String, String> cardNames = new HashMap<>();

    public static String getName(String cardId) {
        return cardNames.get(cardId);
    }

    public static void setName(String cardId, String name) {
        cardNames.put(cardId, name);
    }
}
