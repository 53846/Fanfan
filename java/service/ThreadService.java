package com.example.demo.service;

import com.example.demo.dao.ThreadDao;
import com.example.demo.dto.ThreadDto;
import com.example.demo.entity.ThreadEntity;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThreadService {

    @Resource
    private ThreadDao threadDao;

    public List<ThreadEntity> getThreadByUserId(Integer userId) {
        return threadDao.getThreadByUserId(userId);
    }

    public List<ThreadEntity> getThreadByPage(Integer offset, Integer pageSize) {
        return threadDao.getThreadByPage(offset, pageSize);
    }

    public ThreadEntity getThreadByThreadId(Integer threadId) {
        return threadDao.getThreadByThreadId(threadId);
    }

    public List<String> getImageUrlByThreadId(Integer threadId) {
        return threadDao.getImageUrlByThreadId(threadId);
    }

    public Integer getTotalThreads() {return threadDao.getTotalThreads();}

    public UserEntity getNameAndAvatarByUserId(Integer userId) { return threadDao.getNameAndAvatarByUserId(userId);}

    public void addThread(ThreadDto threadDto) {
        ThreadEntity threadEntity = new ThreadEntity();
        BeanUtils.copyProperties(threadDto, threadEntity);
//        userEntity.setUserName(userRegisterDto.getUserName());
        threadDao.addThread(threadEntity);
    }

}
