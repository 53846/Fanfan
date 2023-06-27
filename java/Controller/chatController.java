package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class chatController {

    @GetMapping("chat")
    public String chat() {
        return "chat";
    }}
