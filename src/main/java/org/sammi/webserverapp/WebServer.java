package org.sammi.webserverapp;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.sammi.webserverapp.RequestHandler;
import org.sammi.webserverapp.Route;

public class WebServer {
    private short port;

    public WebServer(short port) {
        this.port = port;
    }

    public short getPort() {
        return this.port;
    }

    public short start() {
        try {
            InetSocketAddress address = new InetSocketAddress(this.port);
            HttpServer server = HttpServer.create(address, 0);

            // Call handle method on request handler objects for requests to different routes.
            // (See Route.java for details.)
            Route[] routes = Route.getRoutes();
            for (int i = 0; i < routes.length; i++) {
                Route route = routes[i];
                String path = route.getPath();
                RequestHandler.Request requestType = route.getRequestType();
                server.createContext(path, new RequestHandler(requestType));
            }

            // Use default executor.
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }
        return this.port;
    }

    // Entrypoint, set up web server.
    public static void main(String[] args) {
        short port = 8000;
        WebServer server = new WebServer(port);
        short resultPort = server.start();
        System.out.println("Server is running on port " + resultPort);
    }
}

