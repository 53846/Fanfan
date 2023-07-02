package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserModifyDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("userRegister")
    public String register() {
        return "register";
    }

//    @PostMapping("submitRegister")
//    public String submitRegister(UserRegisterDto userRegisterDto, HttpServletRequest request) {
//        userService.addUser(userRegisterDto);
//        return "login";
//    }

    @ResponseBody
    @PostMapping("submitRegister")
    public String submitRegister(@RequestBody UserRegisterDto userRegisterDto) {
        // 检验用户名是否被注册
        List<UserEntity> userEntityList = userService.getByUserName(userRegisterDto.getUserName());
        JSONObject result = new JSONObject();
        result.put("code", 0);
        if (!CollectionUtils.isEmpty(userEntityList)) {
            result.put("msg", "该用户名已经被注册了，请重新输入用户名!");
            return result.toJSONString();
        }
        // 检验手机号是否被注册
        userEntityList = userService.getByPhoneNum(userRegisterDto.getPhoneNum());
        if (!CollectionUtils.isEmpty(userEntityList)) {
            result.put("msg", "该手机号已经被注册了，请重新输入手机号!");
            return result.toJSONString();
        }
        userRegisterDto.setUserAvatar("avatar/dft.png");
        userService.addUser(userRegisterDto);
        result.put("success", true);
        return result.toJSONString();
    }

    @GetMapping("userInfo")
    @ResponseBody
    public String userInfo(Integer userId) {
        UserEntity userEntity = userService.getByUserId(userId);
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("success", true);
        result.put("userInfo", userEntity);
        return result.toJSONString();
    }

    @GetMapping("userModify")
    public String userModify(Integer userId, HttpServletRequest request) {
        UserEntity userEntity = userService.getByUserId(userId);
        request.getSession().setAttribute("userInfo", userEntity);
        return "userModify";
    }

    @PostMapping("updateUser")
    public String submitUserModify(UserModifyDto userModifyDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        userModifyDto.setUserId(user.getUserId());
        userService.updateUser(userModifyDto);
        return "homepage";
    }

}
