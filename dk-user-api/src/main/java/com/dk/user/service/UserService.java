package com.dk.user.service;

import com.dk.user.dto.UserInfo;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/10/29
 */
public interface UserService {

    UserInfo getUserById(int id);

    UserInfo getTeacherById(int id);

    UserInfo getUserByName(java.lang.String username);

    void regiserUser(UserInfo userInfo);
}
