package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class contentController {

    @GetMapping("content")
    public String content() {
        return "content";
    }
}