package com.dk.course.service;

import com.dk.course.dto.CourseDTO;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈课程业务〉
 *
 * @author dukang
 * @create 2020/10/29
 */
public interface ICourseService {

    List<CourseDTO> courseList();
}
