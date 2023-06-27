package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class privacyPolicyController {

    @GetMapping("privacyPolicy")
    public String privacyPolicy() {
        return "privacyPolicy";
    }
}
