package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    
    @GetMapping("homepage")
    public String homepage(HttpServletRequest request) {
        request.getSession().removeAttribute("msg");
        return "homepage";
    }
    
    @PostMapping("submitLogin")
    public String submitLogin(UserLoginDto userLoginDto, HttpServletRequest request) {
        UserDto user = new UserDto();
        user.setUsername(userLoginDto.getLogin_username());
        System.out.println("username: " + userLoginDto.getLogin_username() + " password: " + userLoginDto.getLogin_password() + "captcha: " + userLoginDto.getLogin_captcha());
        request.getSession().setAttribute("user", user);
        return "redirect:homepage";
    }
}
