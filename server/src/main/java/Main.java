import handlers.*;
import logging.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8000);
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
