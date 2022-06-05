import database.Store;
import handlers.*;
import helper.NfcLoader;
import helper.ProductLoader;
import io.FileManager;
import logging.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Store store = new Store();

            String productsJson = FileManager.read("products.json");
            ProductLoader.loadProducts(productsJson, store.products(), store.localisations());

            String nfcJson = FileManager.read("nfcs.json");
            NfcLoader.loadNfcs(nfcJson, store.nfcs());

            int port = 8000;

            String rawPort = System.getenv("PORT");
            if (rawPort != null) {
                port = Integer.parseInt(rawPort);
            }

            Server server = new Server(store, port);
            server.route("/cards", new CardHandler());
            server.route("/points", new PointsHandler());
            server.route("/products", new ProductHandler());
            server.route("/admin", new AdminHandler());
            server.route("/cdn", new CdnHandler());
            server.route("/nfc", new NfcHandler());

            server.start();

        } catch (IOException ex) {
            Logger.fatal(ex, "Failed to start server");
        }
    }
}
