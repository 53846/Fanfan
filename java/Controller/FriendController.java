package com.example.demo.controller;

import com.example.demo.dto.FriendDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.FriendEntity;
import com.example.demo.service.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendController {

    @Resource
    private FriendService friendService;

    @GetMapping("friend")
    public String friend(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        List<FriendEntity> friendEntityList = friendService.getFriendByUserId(user.getUserId());
        List<FriendDto> friendDtoList = new ArrayList<>();
        for (FriendEntity m : friendEntityList) {
            m.setFriendAvatar(friendService.getFriendAvatarByFriendId(m.getFriendId()));
            FriendDto friendDto = new FriendDto();
            friendDto.setFriendAvatar(m.getFriendAvatar());
            friendDto.setFriendName(m.getFriendName());
            friendDto.setFriendId(m.getFriendId());
            friendDto.setLastMsg("默认信息");
            friendDtoList.add(friendDto);
        }
        request.getSession().setAttribute("friend", friendDtoList);
        return "friend";
    }

    @PostMapping("/deleteFriend")
    @ResponseBody
    public String deleteFriend(@RequestParam("friendId") Integer friendId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        // 根据好友ID从数据库中删除好友
        friendService.deleteFriendById(friendId, user.getUserId());
        return "成功删除好友";
    }

    @GetMapping("chat")
    public String chat() {
        return "chat";
    }

}
