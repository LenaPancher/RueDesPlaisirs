package com.example.ruedesplaisirs.controllers;

import com.example.ruedesplaisirs.Services.ArticleDao;
import com.example.ruedesplaisirs.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleDao articles;

    @GetMapping("")
    //@RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        List<Article> List = articles.ListAll();
        model.addAttribute("article", List);

        return "article/index";
    }

}
