package com.example.ruedesplaisirs.controllers;

import com.example.ruedesplaisirs.models.Article;
import com.example.ruedesplaisirs.services.ArticleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleDao articles;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        List<Article> List = articles.ListAll();
        model.addAttribute("article", List);

        return "article/index";
    }

    @RequestMapping(value = "article", method = RequestMethod.GET)
    public String userForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/add";
    }

    @RequestMapping(value = "article", method = RequestMethod.POST)
    public String userSubmit(@ModelAttribute Article article, Model model) {
        articleDao.add(article);
        model.addAttribute("article", article);
        return "redirect:/articles";
    }
}
