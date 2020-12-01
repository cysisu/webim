package com.web.im.chat.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class ChatMessage {
    private String username;
    private String avatar;
    private String id;
    private String name;
    private String type;
    private String content;
    private Integer cid;
    private boolean mine;
    private String formid;
    private Long timestamp;

    public ChatMessage(){
        cid=0;
        mine=false  ;
        timestamp= LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

}
