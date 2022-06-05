package helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.*;
import domain.Card;
import logging.Logger;

public class NfcLoader {
    public static void loadNfcs(String json, NfcStore nfcs) {
        try {
            NfcEntry[] nfcEntries = new ObjectMapper().readValue(json, NfcEntry[].class);

            Logger.info("Matching " + nfcEntries.length + " nfc ids");

            for (NfcEntry nfcEntry : nfcEntries) {
                try {
                    nfcs.set(nfcEntry.nfcId(), nfcEntry.readableId());

                    Logger.debug("Matched nfc '" + nfcEntry.nfcId() + "' to '" + nfcEntry.readableId() + "'");
                } catch (Exception ex) {
                    Logger.warn(ex, "Failed to match nfc '" + nfcEntry.nfcId() + "' to '" + nfcEntry.readableId() + "'");
                }
            }
        } catch (Exception ex) {
            Logger.error(ex, "Failed to match nfcs");
        }
    }

}

record NfcEntry(String nfcId, String readableId) {
}