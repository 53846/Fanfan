package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.ThreadDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.ThreadEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ThreadService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Resource
    private ThreadService threadService;

    @Resource
    private UserService userService;

    @GetMapping("/loadThreads")
    public ResponseEntity<JSONObject> loadThreads(@RequestParam("page") int page) {
        int pageSize = 5; // 每页显示的帖子数量
        int offset = (page - 1) * pageSize; // 计算偏移量

        // 从数据库中查询指定页数的帖子数据
        List<ThreadEntity> threadEntityList = threadService.getThreadByPage(offset, pageSize);

        // 转换为 DTO 对象
        List<ThreadDto> threadDtoList = new ArrayList<>();
        for (ThreadEntity threadEntity : threadEntityList) {
            threadEntity.setImageUrl(threadService.getImageUrlByThreadId(threadEntity.getThreadId()));
            ThreadDto threadDto = new ThreadDto();
            UserEntity userEntity = threadService.getNameAndAvatarByUserId(threadEntity.getUserId());
            threadDto.setUserAvatar(userEntity.getUserAvatar());
            threadDto.setUserNickName(userEntity.getNickName());
            threadDto.setThreadId(threadEntity.getThreadId());
            threadDto.setThreadContent(threadEntity.getThreadContent());
            threadDto.setImageUrl(threadEntity.getImageUrl());
            threadDto.setTimeStamp(threadEntity.getTimeStamp());
            threadDto.setThreadLike(threadEntity.getThreadLike());
            threadDto.setThreadTitle(threadEntity.getThreadTitle());
            threadDto.setUserId(threadEntity.getUserId());
            threadDtoList.add(threadDto);
        }

        // 返回帖子数据及总页数
        int totalThreads = threadService.getTotalThreads(); // 获取总帖子数量
        int totalPage = (int) Math.ceil((double) totalThreads / pageSize); // 计算总页数
        JSONObject result = new JSONObject();
        result.put("totalPage", totalPage);
        result.put("thread", threadDtoList);
        return ResponseEntity.ok()
                .header("Cache-Control", "no-store") // 禁用缓存，以确保每次请求都能获取最新数据
                .body(result);
    }

    @GetMapping("homepage")
    public String homepage(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        UserDto user = (UserDto) session.getAttribute("user");
//        List<ThreadEntity> threadEntity = threadService.getThreadByUserId(user.getUserId());
//        List<ThreadDto> threadList = new ArrayList<>();
//        for (ThreadEntity m : threadEntity) {
//            m.setImageUrl(threadService.getImageUrlByThreadId(m.getThreadId()));
//            ThreadDto threadDto = new ThreadDto();
//            threadDto.setThreadId(m.getThreadId());
//            threadDto.setThreadContent(m.getThreadContent());
//            threadDto.setImageUrl(m.getImageUrl());
//            threadDto.setTimeStamp(m.getTimeStamp());
//            threadDto.setThreadLike(m.getThreadLike());
//            threadDto.setThreadTitle(m.getThreadTitle());
//            threadDto.setUserId(m.getUserId());
//            threadList.add(threadDto);
//        }
//        request.getSession().setAttribute("thread", threadList);
        return "homepage";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:login";
    }

}
