package dongverine.websocket.wolserverjetty.server;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EventSocket extends WebSocketAdapter {
    private final Logger logger = LoggerFactory.getLogger(EventSocket.class);

    /*웹소켓 연결*/
    @Override
    public void onWebSocketConnect(Session sess) {
        super.onWebSocketConnect(sess);
        logger.info("Socket Connected : " + sess);
    }

    /*웹소켓 요청에 대한 처리*/
    @Override
    public void onWebSocketText(String message) {
        super.onWebSocketText(message);
        RemoteEndpoint remote = super.getSession().getRemote();
        if (message.equals("monitoringData")) {
            /* 브라우저로 부터 monitoringData 이벤트 요청이 왔을 때 처리 */

            /*String data를 리턴할 경우 예*/
            try {
                remote.sendString("브라우저로 전송할 데이터");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*웹소켓이 닫혔을 경우*/
    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
        logger.info("Socket Closed: [" + statusCode + "] " + reason);
    }

    /*에러가 발생했을 경우*/
    @Override
    public void onWebSocketError(Throwable cause) {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}