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
//    @GetMapping("Index")
//    public String index() {
//        return "Hello World";
//    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("content")
    public String content() {
        return "content";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @GetMapping("friend")
    public String friend() {
        return "friend";
    }

    @GetMapping("chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("privacyPolicy")
    public String privacyPolicy() {
        return "privacyPolicy";
    }

    @PostMapping("submitLogin")
    public String submitLogin(UserLoginDto userLoginDto, HttpServletRequest request) {
        UserDto user = new UserDto();
        user.setUsername(userLoginDto.getLogin_username());
        System.out.println("username: " + userLoginDto.getLogin_username() + " password: " + userLoginDto.getLogin_password() + "captcha: " + userLoginDto.getLogin_captcha());
        request.getSession().setAttribute("user", user);
        return "redirect:homepage";
    }

    @PostMapping("submitRegister")
    public String submitRegister(UserRegisterDto userRegisterDto) {
        System.out.println("password:" + userRegisterDto.getRegister_password() + " confirmPassword:" + userRegisterDto.getRegister_confirmPassword() + " nickname:" + userRegisterDto.getRegister_nickname());
        return "redirect:login";
    }

//    @GetMapping("index")
//    public String index(HttpServletRequest request) {
//        request.getSession().removeAttribute("msg");
//        return "index";
//    }

    @GetMapping("homepage")
    public String homepage(HttpServletRequest request) {
        request.getSession().removeAttribute("msg");
        return "homepage";
    }
}
