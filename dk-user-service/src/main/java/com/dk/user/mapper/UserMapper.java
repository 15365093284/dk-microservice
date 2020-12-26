package com.dk.user.mapper;

import com.dk.user.dto.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/10/31
 */
@Component
public interface UserMapper {

    @Select("select id,username, password, real_name as realName," +
            "mobile, email from user where id=#{id}")
    UserInfo getUserById(@Param("id")int id);


    @Select("select id,username, password, real_name as realName," +
            "mobile, email from user where username=#{username}")
    UserInfo getUserByName(@Param("username")String username);


    @Insert("insert into user (username, password, real_name, mobile, email)" +
            "values (#{u.username}, #{u.password}, #{u.realName}, #{u.mobile}, #{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);


    @Select("select u.id,u.username,u.password,u.real_name as realName," +
            "u.mobile,u.email,t.intro,t.stars from user u," +
            "teacher t where u.id=#{id} " +
            "and u.id=t.user_id")
    UserInfo getTeacherById(@Param("id")int id);
}
