import database.Store;
import handlers.*;
import helper.ProductLoader;
import io.FileManager;
import logging.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Store store = new Store();

            String products = FileManager.read("src/main/resources/products.json");
            ProductLoader.loadProducts(products, store.products(), store.localisations());

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

            server.start();

        } catch (IOException ex) {
            Logger.fatal(ex, "Failed to start server");
        }
    }
}
