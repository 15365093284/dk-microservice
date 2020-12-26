package com.dk.course;

import com.dk.course.filter.CourseFilter;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@EnableDubbo
@SpringBootApplication
public class CourseEdgeServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(CourseEdgeServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(CourseFilter courseFilter ) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(courseFilter);

        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }
}
