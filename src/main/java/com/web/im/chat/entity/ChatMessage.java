package com.web.im.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class ChatMessage implements Serializable {
    private String username;
    private String avatar;
    private String id;
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
