package dongverine.websocket.wolserverjetty.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServer{
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    public void start(){
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(13701);
        server.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Add a websocket to a specific path spec
        ServletHolder holderEvents = new ServletHolder("ws-events", EventServlet.class);
        //new ServletHolder("ㅈㄴ",
        context.addServlet(holderEvents, "/events/*");

        try{
            server.start();
            server.dump(System.err);
            server.join();
        }catch (Throwable t){
            //logger.error("WebSocket Server Start Error", t);
            t.printStackTrace(System.err);
        }
    }
}