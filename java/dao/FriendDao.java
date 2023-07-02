package com.example.demo.dao;

import com.example.demo.entity.FriendEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendDao {
    List<FriendEntity> getFriendIdAndNameByUserId(@Param(value = "userId") Integer userId);

    String getFriendAvatarByFriendId(@Param(value = "friendId") Integer friendId);

    void deleteFriendById(@Param(value = "friendId") Integer friendId, @Param(value = "userId") Integer userId);
}
