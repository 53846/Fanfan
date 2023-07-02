package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.ThreadDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.ThreadEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ThreadService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ThreadController {

    @Resource
    private ThreadService threadService;

    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("postThread")
    public String postThread(@RequestBody ThreadDto threadDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        threadDto.setUserId(user.getUserId());

        JSONObject result = new JSONObject();
        result.put("code", 0);

        threadService.addThread(threadDto);
        result.put("success", true);
        return result.toJSONString();
    }

    @GetMapping("content")
    public String content(@RequestParam("threadId") Integer threadId, HttpServletRequest request) {

        HttpSession session = request.getSession();

        ThreadEntity threadEntity = threadService.getThreadByThreadId(threadId);
        UserEntity userEntity = userService.getByUserId(threadEntity.getUserId());
        threadEntity.setUserAvatar(userEntity.getUserAvatar());
        threadEntity.setUserNickName(userEntity.getNickName());
        threadEntity.setImageUrl(threadService.getImageUrlByThreadId(threadEntity.getThreadId()));

        ThreadDto threadDto = new ThreadDto();
        threadDto.setThreadContent(threadEntity.getThreadContent());
        threadDto.setThreadTitle(threadEntity.getThreadTitle());
        threadDto.setThreadId(threadEntity.getThreadId());
        threadDto.setThreadLike(threadEntity.getThreadLike());
        threadDto.setTimeStamp(threadEntity.getTimeStamp());
        threadDto.setImageUrl(threadEntity.getImageUrl());
        threadDto.setUserNickName(threadEntity.getUserNickName());
        threadDto.setUserAvatar(threadEntity.getUserAvatar());

        request.getSession().setAttribute("threadDto", threadDto);
        return "content";
    }

    @GetMapping("postThread")
    public String postThread() {
        return "postThread";
    }
}
