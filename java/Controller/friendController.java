package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class friendController {

    @GetMapping("friend")
    public String friend() {
        return "friend";
    }
}
