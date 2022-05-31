import com.sun.net.httpserver.HttpServer;
import handlers.Handler;
import logging.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    private final HttpServer server;

    public Server(int port) throws IOException {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
            this.server.setExecutor(Executors.newCachedThreadPool());
        } catch (IOException ex) {
            Logger.error(ex, "Could not create server");
            throw ex;
        }
    }

    public void route(String path, Handler handler) {
        try {
            this.server.createContext(path, handler);
            Logger.debug("Added handler for " + path);
        } catch (Exception ex) {
            Logger.warn(ex, "Could not add handler for " + path);
        }
    }

    public void start() {
        server.start();
        Logger.info("Server running on port " + server.getAddress().getPort());
    }

    public void stop() {
        server.stop(0);
        Logger.info("Server stopped");
    }
}
