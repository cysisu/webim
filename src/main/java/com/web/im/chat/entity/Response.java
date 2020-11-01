package com.web.im.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    Integer code;
    String msg;
    Object data;
}
