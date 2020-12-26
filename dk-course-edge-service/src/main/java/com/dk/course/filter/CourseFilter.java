package com.dk.course.filter;

import com.dk.oauth.LoginFilter;
import com.dk.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dukang
 * @create 2020/11/1
 */
public class CourseFilter extends LoginFilter {

    @Value("${user.edge.service.addr}")
    private String userEdgeServiceAddr;

    @Override
    protected String userEdgeServiceAddr() {
        return userEdgeServiceAddr;
    }

    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }
}
