package com.web.im.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InitController {
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String getIndex(Model model){
        return "index.html";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLogin(Model model){
        return "login.html";
    }
}
