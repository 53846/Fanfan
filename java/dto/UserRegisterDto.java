package com.example.demo.dto;

public class UserRegisterDto {
    private String register_password;
    private String register_confirmPassword;
    private String register_nickname;

    public String getRegister_password() {
        return register_password;
    }

    public void setRegister_password(String register_password) {
        this.register_password = register_password;
    }

    public String getRegister_confirmPassword() {
        return register_confirmPassword;
    }

    public void setRegister_confirmPassword(String register_confirmPassword) {
        this.register_confirmPassword = register_confirmPassword;
    }

    public String getRegister_nickname() {
        return register_nickname;
    }

    public void setRegister_nickname(String register_nickname) {
        this.register_nickname = register_nickname;
    }
}
