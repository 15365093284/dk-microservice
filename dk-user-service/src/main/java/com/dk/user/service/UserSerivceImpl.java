package com.dk.user.service;

import com.dk.user.dto.UserInfo;
import com.dk.user.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/10/31
 */
@DubboService(version = "1.0.0",interfaceClass = UserService.class)
public class UserSerivceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserInfo getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getTeacherById(int id) {
        return userMapper.getTeacherById(id);
    }

    @Override
    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public void regiserUser(UserInfo userInfo) {
        userMapper.registerUser(userInfo);
    }
}
