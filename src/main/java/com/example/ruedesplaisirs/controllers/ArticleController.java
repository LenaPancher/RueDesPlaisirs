package com.example.ruedesplaisirs.controllers;

import com.example.ruedesplaisirs.models.Article;
import com.example.ruedesplaisirs.services.ArticleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        List<Article> List = articleDao.ListAll();
        model.addAttribute("article", List);
        return "article/index";
    }

    @RequestMapping(value = "article", method = RequestMethod.GET)
    public String articleForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/add";
    }

    @RequestMapping(value = "article", method = RequestMethod.POST)
    public String articleSubmit(@ModelAttribute Article article, Model model) {
        articleDao.add(article);
        model.addAttribute("article", article);
        return "redirect:/articles";
    }
}
