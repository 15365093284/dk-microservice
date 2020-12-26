package com.dk.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo
@SpringBootApplication
public class UserEdgeServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(UserEdgeServiceApplication.class, args);
    }
}
