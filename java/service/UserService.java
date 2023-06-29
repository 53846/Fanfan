package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserModifyDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public UserEntity getByUserNameAndPassword(String userName, String password) {
        List<UserEntity> userList = userDao.getByUserNameAndPassword(userName, password);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    public void addUser(UserRegisterDto userRegisterDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRegisterDto, userEntity);
//        userEntity.setUserName(userRegisterDto.getUserName());
        userDao.addUser(userEntity);
    }

    public UserEntity getByUserId(Integer userId) {
        return userDao.getByUserId(userId);
    }

    public void updateUser(UserModifyDto userModifyDto) {
        userDao.updateUser(userModifyDto);
    }
}
