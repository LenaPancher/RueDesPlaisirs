package com.example.ruedesplaisirs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @GetMapping("/article")
    public String index() {
        return "article/index";
    }
}
