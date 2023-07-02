package com.example.demo.dao;

import com.example.demo.dto.UserModifyDto;
import com.example.demo.entity.ThreadEntity;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserDao {

    List<UserEntity> getByUserNameAndPassword(@Param(value = "userName") String userName, @Param(value = "password") String password);

    void addUser(UserEntity userEntity);

    UserEntity getByUserId(@Param(value = "userId") Integer userId);

    void updateUser(UserModifyDto userModifyDto);

    List<UserEntity> getByUserName(@Param(value = "userName") String userName);

    List<UserEntity> getByPhoneNum(@Param(value = "phoneNum") String phoneNum);
}