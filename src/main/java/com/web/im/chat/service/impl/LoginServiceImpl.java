package com.web.im.chat.service.impl;

import com.web.im.chat.dao.LoginDao;
import com.web.im.chat.entity.User;
import com.web.im.chat.service.LoginService;
import com.web.im.chat.util.RegisterEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginDao loginDao;

    @Override
    public User getUser(String userName, String password) {
        return loginDao.getUser(userName,password);
    }

    @Override
    public RegisterEnum register(String userName, String password) {
        return loginDao.register(userName,password);
    }
}
