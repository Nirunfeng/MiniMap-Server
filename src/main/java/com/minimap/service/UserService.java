package com.minimap.service;

import com.minimap.pojo.User;

public interface UserService {
    User findUserById(String id);

    /*根据用户名查找User*/
    User findUserByUsername(String username);

    /*添加用户方法*/
    User insertUser(User user);

    /*更新昵称方法*/
    User updateUserInfo(User user);
}
