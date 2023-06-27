package com.example.demo.interceptor;

import com.example.demo.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求[" + request.getRequestURI() + "]进入拦截器的preHandle方法，进行用户登录验证...");
        HttpSession session = request.getSession();
        try {
            UserDto user = (UserDto) session.getAttribute("user");
            if (user != null && user.getUsername() != null) {
                System.out.println("用户已登录，可以继续访问...");
                return true;
            }
        } catch (Exception e) {
            System.out.println("出现系统异常，异常信息如下:\n" + e.getLocalizedMessage());
        }
        System.out.println("用户还未登录，请先登录系统...");
        response.sendRedirect(request.getContextPath() + "/IndexLogin");
        return false;
    }

}
