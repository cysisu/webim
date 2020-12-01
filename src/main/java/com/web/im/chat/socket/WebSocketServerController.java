package com.web.im.chat.socket;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.web.im.chat.entity.ChatMessage;
import com.web.im.chat.util.WebSocketDecoder;
import com.web.im.chat.util.WebSocketEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint(value = "/forshow/websocket/{sid}", decoders = {WebSocketDecoder.class}, encoders = {WebSocketEncoder.class})
public class WebSocketServerController {

    // 保存 组id->组成员 的映射关系
    private static ConcurrentHashMap<String, Session> singleMemberInfoMap = new ConcurrentHashMap<>();

    // 收到消息调用的方法
    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(message).getJSONObject("data");
            ChatMessage mine = jsonObject.getObject("mine", ChatMessage.class);
            ChatMessage to = jsonObject.getObject("to", ChatMessage.class);

            log.info("[onMessage] message: {}", jsonObject);
            log.info("[onMessage] mine: {}", mine.toString());
            log.info("[onMessage] to: {}", to.toString());

            String toId=to.getId();
            to.setUsername(mine.getUsername());
            to.setAvatar(mine.getAvatar());
            to.setId(mine.getId());
            to.setType("friend");
            to.setContent(mine.getContent());
            to.setFormid(mine.getFormid());
            if (singleMemberInfoMap.containsKey(to.getId())) {
                Session session1 = singleMemberInfoMap.get(toId);
                to.setMine(false);
                log.info("[sendMessage][to] to: {}", to.toString());
                session1.getBasicRemote().sendText(JSONUtil.toJsonStr(to));
            }
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 建立连接调用的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        singleMemberInfoMap.put(sid, session);
        log.info("[onOpen] sid: {} on connected", sid);
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
