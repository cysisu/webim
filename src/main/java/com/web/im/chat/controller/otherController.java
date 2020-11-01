package com.web.im.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/test")
public class otherController {
    @PostMapping("/login")
    @ResponseBody
    public String login(String userName,String password){
        return "login success, userName:"+userName+",passWord: "+password;
    }
}
