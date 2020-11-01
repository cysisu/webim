package com.web.im.chat.controller;

import com.web.im.chat.dao.InitDao;
import com.web.im.chat.entity.Response;
import com.web.im.chat.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class InitController {

    @Autowired
    private InitService initService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String getIndex(Model model){
        return "index.html";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLogin(Model model){
        return "login.html";
    }

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    @ResponseBody
    public Object getInitFriend(HttpSession session){
        Integer id=Integer.parseInt( session.getAttribute("id").toString());
        return new Response(0,"",initService.getInitFriend(id));
    }
}
