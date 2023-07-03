package com.example.demo.controller;

import cn.hutool.captcha.GifCaptcha;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Resource
    private GifCaptcha gifCaptcha;

    @Resource
    private UserService userService;

    @GetMapping("indexLogin")
    public String indexLogin() {
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }
    /**
     * ajax异步请求后台接口
     *
     * @param loginDTO
     * @return
     */
    @ResponseBody
    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("用户填写的登录信息:" + JSONObject.toJSONString(loginDto));
        boolean captchaValidResult =  gifCaptcha.verify(loginDto.getLogin_captcha());
        System.out.println("验证码验证结果:" + captchaValidResult);
        JSONObject result = new JSONObject();
        result.put("code", 0);
        // 当用户输入的验证码错误，直接返回错误提示信息给前端
        if (!captchaValidResult) {
            result.put("success", false);
            result.put("msg", "请输入正确的验证码!");
            return result.toJSONString();
        }
        // 当验证码检验通过，则查询用户信息是否输入正确
        UserEntity userEntity = userService.getByUserNameAndPassword(loginDto.getLogin_username(), loginDto.getLogin_password());
        if (userEntity == null) {
            result.put("success", false);
            result.put("msg", "用户名或者密码输入错误，请重新输入!");
            return result.toJSONString();
        }
        result.put("success", true);
        result.put("msg", "登录成功");
        // 登录成功，将用户信息存入session中
        UserDto user = new UserDto();
        user.setUserId(userEntity.getUserId());
        user.setNickName(userEntity.getNickName());
        user.setUserName(userEntity.getUserName());
        user.setUserAvatar(userEntity.getUserAvatar());
        request.getSession().setAttribute("user", user);

        return result.toJSONString();
    }

    /**
     * form 表单请求的后台接口
     * @param loginDto
     * @return
     */
//    @PostMapping("submitLogin")
//    public String submitLogin(LoginDTO loginDTO, HttpServletRequest request) {
//        System.out.println("用户填写的登录信息:" + JSONObject.toJSONString(loginDTO));
//
////        boolean result =  gifCaptcha.verify(loginDTO.getLogin_captcha());
////        System.out.println("验证码验证结果:" + result);
//        UserEntity userEntity = userService.getByUserNameAndPassword(loginDTO.getLogin_username(), loginDTO.getLogin_password());
//        HttpSession httpSession = request.getSession();
//        if (userEntity == null) {
//            httpSession.setAttribute("msg", "用户名或者密码输入错误，请重新输入!");
//            return "redirect:login";
//        }
//
//        UserDto user = new UserDto();
//        user.setId(userEntity.getId());
//        user.setUsername(loginDTO.getLogin_username());
//        httpSession.setAttribute("user", user);
//        return "homepage";
//
//    }

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

    @GetMapping("privacyPolicy")
    public String privacyPolicy() {
        return "privacyPolicy";
    }
}