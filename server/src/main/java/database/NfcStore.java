package database;

import java.util.HashMap;

public class NfcStore {
    private final HashMap<String, String> ids;

    public NfcStore() {
        this.ids = new HashMap<String, String>();
    }

    public void set(String nfcId, String readableId) {
        ids.put(nfcId, readableId);
    }

    public String getReadableId(String nfcId) {
        return ids.get(nfcId);
    }

    public String getNfcId(String readableId) {
        for (String nfcId : ids.keySet()) {
            if (ids.get(nfcId).equals(readableId)) {
                return nfcId;
            }
        }
        return null;
    }
}
