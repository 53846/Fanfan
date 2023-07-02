package com.example.demo.dao;

import com.example.demo.entity.ThreadEntity;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ThreadDao {

    List<ThreadEntity> getThreadByUserId(@Param(value = "userId") Integer userId);

    List<ThreadEntity> getThreadByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    ThreadEntity getThreadByThreadId(@Param("threadId") int threadId);

    List<String> getImageUrlByThreadId(@Param(value = "threadId") Integer threadId);

    Integer getTotalThreads();

    UserEntity getNameAndAvatarByUserId(@Param(value = "userId") Integer userId);

    void addThread(ThreadEntity threadEntity);
}
