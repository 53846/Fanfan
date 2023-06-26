package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    /**
     * ajax异步请求后台接口
     *
     * @param loginDTO
     * @return
     */
    @ResponseBody
    @PostMapping("login")
    public String login(@RequestBody LoginDTO loginDTO) {
        System.out.println("用户填写的登录信息:" + JSONObject.toJSONString(loginDTO));
        return "登录成功...";
    }

    /**
     * form 表单请求的后台接口
     * @param loginDTO
     * @return
     */
    @ResponseBody
    @PostMapping("syncLogin")
    public String syncLogin(LoginDTO loginDTO) {
        System.out.println("用户填写的登录信息:" + JSONObject.toJSONString(loginDTO));
        return "登录成功...";
    }
}