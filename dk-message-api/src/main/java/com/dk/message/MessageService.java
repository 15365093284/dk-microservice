package com.dk.message;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/11/2
 */
public interface MessageService {

    public boolean sendMobileMessage(String mobile, String message);

    public boolean sendEmailMessage(String email,String message);
}
