package com.dk.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@MapperScan("com.dk.user.mapper")//扫描指定包下接口
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
