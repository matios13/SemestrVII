package Sensor;

import com.sun.javafx.collections.MappingChange;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by matio_000 on 07.11.2016.
 */
public class TEST{

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/info", new InfoHandler());
        server.createContext("/get", new GetHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("The server is running");
    }

    // http://localhost:8000/info
    static class InfoHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            String response = "Use /get?hello=word&foo=bar to see how to handle url parameters";
            //TEST.writeResponse(httpExchange, response.toString());
        }
    }

    static class GetHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            Map<String,String> parms = TEST.queryToMap(httpExchange.getRequestURI().getQuery());
            String response = "Hello : "+ parms.get("hello")+"\n foo : "+parms.get("foo");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

}