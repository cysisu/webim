package com.web.im.chat.util;

import com.alibaba.fastjson.JSON;
import com.web.im.chat.entity.ChatMessage;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

public class WebSocketDecoder implements javax.websocket.Decoder.Text<ChatMessage> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(EndpointConfig arg0) {
    }

    @Override
    public ChatMessage decode(String user) throws DecodeException {
        return JSON.parseObject(user, ChatMessage.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }

}