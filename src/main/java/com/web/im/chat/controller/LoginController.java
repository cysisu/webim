package com.web.im.chat.controller;

import com.web.im.chat.entity.User;
import com.web.im.chat.service.LoginService;
import com.web.im.chat.util.RegisterEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/username")
    public String getUsername(){
        return "user";
    }

    @RequestMapping
    @ResponseBody
    public String getLogin(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String login(String userName, String password, HttpSession session){
        log.info("login for username:{},password:{}",userName,password);
        User user=loginService.getUser(userName,password);
        if(user==null){
            return "fail";
        }
        session.setAttribute("id",user.getId());
        session.setAttribute("userName",user.getName());
        session.setAttribute("status",user.getStatus());
        session.setAttribute("sign",user.getSign());
        session.setAttribute("avatar",user.getAvatar());
        return "success";
    }

    @PostMapping("/register")
    public String register(String userName,String password){
        log.info("register for user:{}",userName);
        RegisterEnum registerEnum = loginService.register(userName,password);
        if(registerEnum==RegisterEnum.SUCCESS){
            return "success";
        }
        else if(registerEnum==RegisterEnum.ALREADYEXIST){
            return "already";
        }
        else{
            return "fail";
        }
    }
}
