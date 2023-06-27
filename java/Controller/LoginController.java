package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {
        return "login";
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
