package com.example.demo.controller;

import com.example.demo.dto.UserModifyDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class userController {

    @Resource
    private UserService userService;

    @GetMapping("userRegister")
    public String register() {
        return "register";
    }

    @PostMapping("submitRegister")
    public String submitRegister(UserRegisterDto userRegisterDto, HttpServletRequest request) {
        userService.addUser(userRegisterDto);
        return "login";
    }

    @GetMapping("userModify")
    public String userModify(Integer userId, HttpServletRequest request) {
        UserEntity userEntity = userService.getByUserId(userId);
        request.getSession().setAttribute("userInfo", userEntity);
        return "userModify";
    }

    @PostMapping("updateUser")
    public String submitUserModify(UserModifyDto userModifyDto) {
        userService.updateUser(userModifyDto);
        return "login";
    }
}
