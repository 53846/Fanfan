package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    /**
     * ajax异步请求后台接口
     *
     * @param registerDto
     * @return
     */
    @ResponseBody
    @PostMapping("register")
    public String login(@RequestBody UserRegisterDto registerDto, HttpServletRequest request) {
        System.out.println("用户填写的登录信息:" + JSONObject.toJSONString(registerDto));
        return "redirect:login";
    }

}