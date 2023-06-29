package com.example.demo.dto;

public class UserModifyDto extends UserRegisterDto {

    /**
     * 用户ID
     */
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
