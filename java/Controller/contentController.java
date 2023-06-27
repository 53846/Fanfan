package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class contentController {

    @GetMapping("content")
    public String content() {
        return "content";
    }
}
