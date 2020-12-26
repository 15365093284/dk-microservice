package com.dk.course.controller;

import com.dk.course.dto.CourseDTO;
import com.dk.course.service.ICourseService;
import com.dk.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/11/1
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @DubboReference(version = "1.0.0")
    private ICourseService courseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseList(HttpServletRequest request) {

        UserDTO user = (UserDTO)request.getAttribute("user");
        System.out.println(user.toString());

        return courseService.courseList();
    }
}
