package com.dk.message.service;

import com.dk.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/11/2
 */
@Slf4j
@DubboService(version = "1.0.0",interfaceClass = MessageService.class)
public class MessageServiceImpl implements MessageService {

    @Override
    public boolean sendMobileMessage(String mobile, String message) {
        log.info("mobile:{},message:{}",mobile,message);
        return true;
    }

    @Override
    public boolean sendEmailMessage(String email, String message) {
        log.info("email:{},message:{}",email,message);
        return true;
    }
}
