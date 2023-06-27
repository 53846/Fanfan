package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("submitRegister")
    public String submitRegister(UserRegisterDto userRegisterDto) {
        System.out.println("password:" + userRegisterDto.getRegister_password() + " confirmPassword:" + userRegisterDto.getRegister_confirmPassword() + " nickname:" + userRegisterDto.getRegister_nickname());
        return "redirect:login";
    }
}
