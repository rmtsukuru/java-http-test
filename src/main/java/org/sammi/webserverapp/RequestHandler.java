package org.sammi.webserverapp;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

import org.sammi.webserverapp.util.FileLoader;

public class RequestHandler implements HttpHandler {
    public enum Request {
        ROOT,
        ABOUT,
        LOGIN,
        STYLE,
        SCRIPT
    }

    private Request requestType;

    public RequestHandler(Request requestType) {
        this.requestType = requestType;
    }

    // Currently unused, see loadHtmlTemplateFile.
    public String getHtmlTemplate() {
        String template = "<html><head><title>$TITLE</title></head><body>" + 
            "<h1>Web Server App</h1><p>$BODY</p>" +
            "</body></html>";
        return template;
    }

    public String loadHtmlTemplateFile() throws IOException {
        return FileLoader.loadFile("src/main/client/template.html");
    }

    // TODO refactor this to make it easier to set a page-specific title to go with each file.
    public String getRequestBodyContent() {
        try {
            switch (this.requestType) {
                case ROOT:
                    return FileLoader.loadFile("src/main/client/index.html");

                case ABOUT:
                    return FileLoader.loadFile("src/main/client/about.html");

                case STYLE:
                    return FileLoader.loadFile("src/main/client/css/style.css");

                case SCRIPT:
                    return FileLoader.loadFile("src/main/client/js/script.js");

                default:
                    return "<p>Sorry, we got lost.</p>";
            }
        } catch (IOException e) {
            return "<h1>An unexpected error occurred loading page " + this.requestType + ".</h1>";
        }
    }

    // Handle client request from exchange.
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        System.out.println(method + " " + path);

        String responseText = this.getRequestBodyContent();
        String response;
        // TODO modify this to check request headers for actual path
        if (this.requestType == Request.STYLE || this.requestType == Request.SCRIPT) {
            response = responseText;
        } else {
            String template = this.loadHtmlTemplateFile();
            response = template.replace("$BODY", responseText).replace("$TITLE", "Sammi Web App");
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream output = exchange.getResponseBody();
        output.write(response.getBytes());
        output.close();
    }
}

