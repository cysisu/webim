package com.web.im.chat.dao;

import com.web.im.chat.entity.User;
import com.web.im.chat.util.RegisterEnum;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@NoArgsConstructor
@Slf4j
public class LoginDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUser(String userName, String password) {
        String sql = "select * from user where name=? and password=?";
        Object[] args = new Object[2];
        args[0] = userName;
        args[1] = password;
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

    public RegisterEnum register(String userName, String password) {
        if (isExistUser(userName))
            return RegisterEnum.ALREADYEXIST;
        String sql = "insert into user values(null,?,?,null,null,null)";
        Object[] args = new Object[2];
        args[0] = userName;
        args[1] = password;
        if(jdbcTemplate.update(sql,args)>0)
            return RegisterEnum.SUCCESS;
        else
            return RegisterEnum.FAIL;
    }

    /**
     * @Description:判断用户是否存在
     *
     * @param userName
     * @return 如果用户存在，返回true,else 返回false
     */
    private boolean isExistUser(String userName) {
        String sql = "select count(*) from user where name=?";
        Object[] args = new Object[1];
        args[0] = userName;
        Integer count = jdbcTemplate.queryForObject(sql, args, Integer.class);
        return count > 0;
    }
}
