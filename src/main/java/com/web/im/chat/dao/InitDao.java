package com.web.im.chat.dao;

import com.web.im.chat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 通过id获取用户信息或者好友个人信息
     *
     * @param id
     * @return
     */
    public User getUser(int id) {
        String sql = "select * from user where id = ?";
        Object[] args = new Object[1];
        args[0] = id;
        List<User> userList = jdbcTemplate.query(sql, args, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setSign(resultSet.getString("sign"));
                user.setStatus(resultSet.getString("status"));
                return user;
            }
        });
        return userList.size() == 0 ? null : userList.get(0);
    }

    /**
     * 通过id获取某个某个用户的分组名
     *
     * @param id
     * @return
     */
    public List<String> getGroupNameList(int id) {
        String sql = "select distinct groupName from friend where Myid = ?";
        Object[] args = new Object[1];
        args[0] = id;
        List<String> groupNameList = jdbcTemplate.queryForList(sql, args, String.class);
        return groupNameList;
    }

    /**
     * 通过groupName获取这个分组下面朋友的id
     *
     * @param groupName
     * @return
     */
    public List<Integer> getGroupFriendList(String groupName) {
        String sql = "select friendId from friend where groupName = ?";
        Object[] args = new Object[1];
        args[0] = groupName;
        List<Integer> friendIdList = jdbcTemplate.queryForList(sql, args, Integer.class);
        return friendIdList;
    }


}
