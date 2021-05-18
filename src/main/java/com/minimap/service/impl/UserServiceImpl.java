package com.minimap.service.impl;

import com.idworker.Sid;
import com.minimap.dao.UserMapper;
import com.minimap.pojo.User;
import com.minimap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * user的service层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired(required = false)
    private Sid sid;

    /**
     * 业务方法findUserById
     * @param id
     * @return user
     */
    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 业务方法findUserByUsername
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 注册业务时调用方法
     * @param user
     * @return
     */
    @Override
    public User insertUser(User user) {
        user.setId(sid.nextShort());
        userMapper.insert(user);
        return user;
    }

    /**
     * 更新用户业务方法
     * @param user
     * @return
     */
    @Override
    public User updateUserInfo(User user) {
        //更改操作
        userMapper.updateByPrimaryKeySelective(user);
        //查询user
        return userMapper.selectByPrimaryKey(user.getId());
    }
}
