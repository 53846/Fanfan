package com.example.demo.dto;

public class UserLoginDto {
    private String login_username;
    private String login_password;
    private String login_captcha;

    public String getLogin_username() {
        return login_username;
    }

    public void setLogin_username(String login_username) {
        this.login_username = login_username;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getLogin_captcha() {
        return login_captcha;
    }

    public void setLogin_captcha(String login_captcha) {
        this.login_captcha = login_captcha;
    }
}
