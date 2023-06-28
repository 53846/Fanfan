package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface UserDao {

    List<UserEntity> getByUserNameAndPassword(@Param(value = "userName") String userName, @Param(value = "password") String password);
}
