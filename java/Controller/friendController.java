package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class friendController {

    @GetMapping("friend")
    public String friend() {
        return "friend";
    }
}
