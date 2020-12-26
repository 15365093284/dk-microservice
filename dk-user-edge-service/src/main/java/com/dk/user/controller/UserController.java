package com.dk.user.controller;

import com.dk.message.MessageService;
import com.dk.user.dto.UserDTO;
import com.dk.user.dto.UserInfo;
import com.dk.user.response.LoginResponse;
import com.dk.user.response.Response;
import com.dk.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/11/2
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    @DubboReference(version = "1.0.0")
    private MessageService messageService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username")String username,
                          @RequestParam("password")String password) {

        //1. 验证用户名密码
        UserInfo userInfo = userService.getUserByName(username);
        if(userInfo==null) {
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if(!userInfo.getPassword().equalsIgnoreCase(md5(password))) {
            return Response.USERNAME_PASSWORD_INVALID;
        }

        //2. 生成token
        String token = genToken();


        //3. 缓存用户
        redisTemplate.opsForValue().set(token, toDTO(userInfo), 3600, TimeUnit.SECONDS);

        return new LoginResponse(token);
    }

    private UserDTO toDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);
        return userDTO;
    }


    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Response sendVerifyCode(@RequestParam(value="mobile", required = false) String mobile,
                                   @RequestParam(value="email", required = false) String email) {

        String message = "Verify code is:";
        String code = randomCode("0123456789", 6);
        boolean result = false;
        if(StringUtils.isNotBlank(mobile)) {
            result = messageService.sendMobileMessage(mobile, message+code);
            redisTemplate.opsForValue().set(mobile, code, 10000, TimeUnit.SECONDS);
        } else if(StringUtils.isNotBlank(email)) {
            result = messageService.sendEmailMessage(email, message+code);
            redisTemplate.opsForValue().set(mobile, code, 10000, TimeUnit.SECONDS);
        } else {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if(!result) {
            return Response.SEND_VERIFYCODE_FAILED;
        }
        return Response.SUCCESS;

    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(value="mobile", required = false) String mobile,
                             @RequestParam(value="email", required = false) String email,
                             @RequestParam("verifyCode") String verifyCode) {

        if(StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if(StringUtils.isNotBlank(mobile)) {
            String redisCode = (String) redisTemplate.opsForValue().get(mobile);
            if(!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }else {
            String redisCode = (String) redisTemplate.opsForValue().get(email);
            if(!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(md5(password));
        userInfo.setMobile(mobile);
        userInfo.setEmail(email);

        userService.regiserUser(userInfo);

        return Response.SUCCESS;
    }

    @RequestMapping(value="/authentication", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO authentication(@RequestHeader("token") String token) {

        return (UserDTO) redisTemplate.opsForValue().get(token);
    }

    private String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for(int i=0;i<size;i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    private String md5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(password.getBytes("utf-8"));
            return HexUtils.toHexString(md5Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
