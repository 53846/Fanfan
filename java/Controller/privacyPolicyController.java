package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class privacyPolicyController {

    @GetMapping("privacyPolicy")
    public String privacyPolicy() {
        return "privacyPolicy";
    }
}
