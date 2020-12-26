package com.dk.course.mapper;

import com.dk.course.dto.CourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/10/31
 */
@Mapper
public interface CourseMapper {

    @Select("select * from course")
    List<CourseDTO> listCourse();

    @Select("select user_id from user_course where course_id=#{courseId}")
    Integer getCourseTeacher(@Param("courseId") int courseId);
}
