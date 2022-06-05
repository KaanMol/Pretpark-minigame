package database;

import java.util.HashMap;

public class NfcStore {
    private final HashMap<String, String> ids;

    public NfcStore() {
        this.ids = new HashMap<String, String>();
    }

    public void set(String nfcId, String cardNumber) {
        ids.put(nfcId, cardNumber);
    }

    public String getcardNumber(String nfcId) {
        return ids.get(nfcId);
    }

    public String getNfcId(String cardNumber) {
        for (String nfcId : ids.keySet()) {
            if (ids.get(nfcId).equals(cardNumber)) {
                return nfcId;
            }
        }
        return null;
    }
}
