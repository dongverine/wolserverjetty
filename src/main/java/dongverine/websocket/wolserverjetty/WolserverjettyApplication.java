package dongverine.websocket.wolserverjetty;

import dongverine.websocket.wolserverjetty.server.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WolserverjettyApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WolserverjettyApplication.class, args);

        WebSocketServer ws = ctx.getBean(WebSocketServer.class);
        //logger.info("■□■□■Monitoring Websocket Server Start■□■□■");
        ws.start();
    }

}
