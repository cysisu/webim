package com.web.im.chat.service.impl;

import com.web.im.chat.dao.InitDao;
import com.web.im.chat.entity.User;
import com.web.im.chat.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class InitServiceImpl implements InitService {
    @Autowired
    private InitDao initDao;

    /**
     * 返回getList.json中data的数据
     * @param id
     * @return
     */
    @Override
    public Object getInitFriend(Integer id) {
        Map<String,Object> result=new HashMap<>();
        User user = initDao.getUser(id);

        Map<String,Object> mine=new HashMap<>();
        mine.put("username",user.getName());
        mine.put("id",user.getId());
        mine.put("status",user.getStatus());
        mine.put("sign",user.getSign());
        mine.put("avatar",user.getSign());

        result.put("mine",mine);
        result.put("friend",getFriends(id));

        return result;
    }

    /**
     * 通过一个用户的id获取这个用户的所有朋友
     *
     * @param myId
     * @return
     */
    private Object getFriends(Integer myId) {
        List<Object> result = new ArrayList<>();
        List<String> groupNameList = initDao.getGroupNameList(myId);
        log.info("the group name list is {} of the user which id is {}",groupNameList.toString(),myId);
        groupNameList.forEach(groupName -> {
            Map<String, Object> group = new HashMap<>();
            group.put("groupname", groupName);
            group.put("id", result.size() + 1);
            group.put("online",1);
            List<Integer> friendList = initDao.getGroupFriendList(groupName);
            log.info("the friendList is {} which the groupName is {}",friendList.toString(),groupName);
            List<Object> friendObjectList = new ArrayList<>();
            friendList.forEach(friendId -> {
                Map<String, Object> friendObject = new HashMap<>();
                User user = initDao.getUser(friendId);
                friendObject.put("username", user.getName());
                friendObject.put("id", user.getId());
                friendObject.put("avatar", user.getAvatar());
                friendObject.put("sign", user.getSign());
                friendObjectList.add(friendObject);
            });
            group.put("list", friendObjectList);
            result.add(group);
        });

        return result;
    }
}
