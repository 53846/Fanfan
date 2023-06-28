package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
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
}
