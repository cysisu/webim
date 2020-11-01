package com.web.im.chat.service;

import com.web.im.chat.entity.User;
import com.web.im.chat.util.RegisterEnum;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface LoginService {
    User getUser(String userName,String password);
    RegisterEnum register(String userName,String password);
}
