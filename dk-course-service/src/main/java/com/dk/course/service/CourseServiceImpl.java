package com.dk.course.service;

import com.dk.course.dto.CourseDTO;
import com.dk.course.mapper.CourseMapper;
import com.dk.user.dto.TeacherDTO;
import com.dk.user.dto.UserInfo;
import com.dk.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈课程业务〉
 *
 * @author dukang
 * @create 2020/10/29
 */
@DubboService(version = "1.0.0",interfaceClass = ICourseService.class)
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<CourseDTO> courseList() {

        List<CourseDTO> courseDTOS = courseMapper.listCourse();
        if(courseDTOS!=null) {
            for(CourseDTO courseDTO : courseDTOS) {
                Integer teacherId = courseMapper.getCourseTeacher(courseDTO.getId());
                if(teacherId!=null) {
                    try {
                        UserInfo userInfo = userService.getTeacherById(teacherId);
                        courseDTO.setTeacher(trans2Teacher(userInfo));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return courseDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(userInfo, teacherDTO);
        return teacherDTO;
    }
}
