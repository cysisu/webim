package com.web.im.chat.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@Slf4j
@ServerEndpoint("/forshow/websocket/{sid}")
public class WebSocketServerController {

    // 收到消息调用的方法
    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("[onMessage] message: {}",message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 建立连接调用的方法
    @OnOpen
    public void onOpen(@PathParam("sid") String sid) {
        log.info("[onOpen] sid: {} on connected",sid);
    }

    // 关闭连接调用的方法
    @OnClose
    public void onClose() {
        log.info("[onClose] sid close'");
    }

    // 传输消息错误调用的方法
    @OnError
    public void OnError(Throwable error) {
        System.out.println("Connection error");
    }
}
