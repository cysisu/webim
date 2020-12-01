package com.web.im.chat.util;

import com.alibaba.fastjson.JSON;
import com.web.im.chat.entity.ChatMessage;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

public class WebSocketEncoder implements javax.websocket.Encoder.Text<ChatMessage> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(EndpointConfig arg0) {
    }

    @Override
    public String encode(ChatMessage param) throws EncodeException {
        return JSON.toJSONString(param);
    }

}