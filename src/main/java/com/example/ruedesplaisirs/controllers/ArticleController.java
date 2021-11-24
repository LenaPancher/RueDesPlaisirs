package com.example.ruedesplaisirs.controllers;

import com.example.ruedesplaisirs.models.Article;
import com.example.ruedesplaisirs.services.ArticleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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


    @RequestMapping(value = "article/{id}", method = RequestMethod.GET)
    public String showArticle(@PathVariable int id, Model model) {
        Article article = articleDao.show(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    @RequestMapping(value = "article/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("article", articleDao.show(id));
        return "article/edit";
    }
    @RequestMapping(value = "article/edit/{id}", method = RequestMethod.POST)
    public String editArticle(@ModelAttribute Article article, @PathVariable int id) {
        articleDao.edit(article, id);
        return "redirect:/articles";
    }
    
    @RequestMapping(value = "article/delete/{id}", method = RequestMethod.GET)
    public String articleDelete(@PathVariable int id) {
        int article = articleDao.delete(id);

        return "redirect:/articles";
    }
}
