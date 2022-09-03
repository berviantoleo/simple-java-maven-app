package com.mycompany.app;

import com.sun.net;
/**
 * Hello world!
 */
public class App
{
    public App() {}

    public static void main(String[] args) {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/test", new  MyHttpHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
        logger.info(" Server started on port 8001");
    }

}
