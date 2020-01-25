package com.bhhan.springbootmvc.web;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2020-01-25
 * Github : http://github.com/bhhan5274
 */
public class VisitTimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpSession session = request.getSession();
        if(session.getAttribute("visitTime") == null){
            session.setAttribute("visitTime", LocalDateTime.now());
        }
        return true;
    }
}
