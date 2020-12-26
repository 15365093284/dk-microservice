package com.dk.user.dto;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/10/31
 */
@Data
public class UserInfo {

    public int id; // required
    public java.lang.String username; // required
    public java.lang.String password; // required
    public java.lang.String realName; // required
    public java.lang.String mobile; // required
    public java.lang.String email; // required
    public java.lang.String intro; // required
    public int stars; // required
}
