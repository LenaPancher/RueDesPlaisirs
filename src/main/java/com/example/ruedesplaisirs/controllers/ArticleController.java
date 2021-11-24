package com.example.ruedesplaisirs.controllers;

import com.example.ruedesplaisirs.models.Article;
import com.example.ruedesplaisirs.services.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @GetMapping("/articles")
    public String index() {
        return "article/index";
    }

    @GetMapping("/article")
    public String userForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/add";
    }

    @PostMapping("/article")
    public String userSubmit(@ModelAttribute Article article, Model model) {
        articleDao.add(article);
        model.addAttribute("article", article);
        return "redirect:/articles";
    }
}
