package com.example.demo.interceptor;

import com.example.demo.dto.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求[" + request.getRequestURI() + "]进入拦截器的preHandle方法，进行用户登录验证...");
        HttpSession session = request.getSession();
        try {
            UserDto user = (UserDto) session.getAttribute("user");
            if (user != null && user.getUserName() != null) {
                System.out.println("用户已登录，可以继续访问...");
                return true;
            }
        } catch (Exception e) {
            System.out.println("出现系统异常，异常信息如下:\n" + e.getLocalizedMessage());
        }
        System.out.println("用户还未登录，请先登录系统...");
        response.sendRedirect(request.getContextPath() + "/indexLogin");
        return false;
    }

}
