package com.example.demo.controller;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.GifCaptcha;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Resource
    private GifCaptcha gifCaptcha;

    @Resource
    private UserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("submitLogin")
    public String submitLogin(LoginDTO loginDTO, HttpServletRequest request) {
        System.out.println("用户填写的登录信息:" + JSONObject.toJSONString(loginDTO));

//        boolean result =  gifCaptcha.verify(loginDTO.getLogin_captcha());
//        System.out.println("验证码验证结果:" + result);
        UserEntity userEntity = userService.getByUserNameAndPassword(loginDTO.getLogin_username(), loginDTO.getLogin_password());
        HttpSession httpSession = request.getSession();
        if (userEntity == null) {
            httpSession.setAttribute("msg", "用户名或者密码输入错误，请重新输入!");
            return "redirect:login";
        }

        UserDto user = new UserDto();
        user.setUsername(loginDTO.getLogin_username());
        httpSession.setAttribute("user", user);
        return "homepage";

    }

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) {
        gifCaptcha.createCode();

        String code = gifCaptcha.getCode();
        System.out.println("系统生成的验证码:" + code);
        try {
            response.setContentType("image/gif");
            gifCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            System.out.println("生成验证码异常，异常信息如下:\n" + e.getLocalizedMessage());
        }
    }
}