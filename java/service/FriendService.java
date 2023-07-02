package com.example.demo.service;

import com.example.demo.dao.FriendDao;
import com.example.demo.entity.FriendEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendService {

    @Resource
    private FriendDao friendDao;

    public List<FriendEntity> getFriendByUserId(Integer userId) {
        return friendDao.getFriendIdAndNameByUserId(userId);
    }

    public String getFriendAvatarByFriendId(Integer friendId) {
        return friendDao.getFriendAvatarByFriendId(friendId);
    }

    public void deleteFriendById(Integer friendId, Integer userId) { friendDao.deleteFriendById(friendId, userId); }
}
