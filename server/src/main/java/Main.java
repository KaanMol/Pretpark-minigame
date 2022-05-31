import handlers.AdminHandler;
import handlers.CardHandler;
import handlers.PointsHandler;
import logging.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8000);
            server.route("/cards", new CardHandler());
            server.route("/points", new PointsHandler());
            server.route("/admin", new AdminHandler());
            server.start();

        } catch (IOException ex) {
            Logger.fatal(ex, "Failed to start server");
        }
    }
}
