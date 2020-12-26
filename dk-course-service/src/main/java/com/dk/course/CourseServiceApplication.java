package com.dk.course;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@MapperScan("com.dk.course.mapper")//扫描指定包下接口
@SpringBootApplication
public class CourseServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }
}
