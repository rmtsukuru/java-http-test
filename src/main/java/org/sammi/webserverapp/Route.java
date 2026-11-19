package org.sammi.webserverapp;

import org.sammi.webserverapp.RequestHandler;

public class Route {
    private String path;
    private RequestHandler.Request requestType;

    private static Route[] routes = {
        new Route("/", RequestHandler.Request.ROOT),
        new Route("/about", RequestHandler.Request.ABOUT),
        new Route("/login", RequestHandler.Request.ROOT),
        new Route("/css/", RequestHandler.Request.STYLE),
        new Route("/js/", RequestHandler.Request.SCRIPT)
    };

    public Route(String path, RequestHandler.Request requestType) {
        this.path = path;
        this.requestType = requestType;
    }

    public String getPath() {
        return this.path;
    }

    public RequestHandler.Request getRequestType() {
        return this.requestType;
    }

    public static Route[] getRoutes() {
        return routes;
    }
}

