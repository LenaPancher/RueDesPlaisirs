package com.example.ruedesplaisirs.controllers;

import com.example.ruedesplaisirs.models.Article;

import com.example.ruedesplaisirs.services.ArticlesDao;
import com.example.ruedesplaisirs.services.ArticlesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticlesDao articlesDao;

    /** This function makes it possible to display all the article from de database
     *
     * @param model
     * @return index page
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        List<Article> List = articlesDao.ListAll();
        model.addAttribute("article", List);
        return "article/index";
    }

    @RequestMapping(value = "/categorie_femmes", method = RequestMethod.GET)
    public String articlesFemmes(Model model) {
        List<Article> List = articlesDao.ListFemmes();
        model.addAttribute("article", List);
        return "article/categorie_femmes";
    }

    @RequestMapping(value = "/categorie_hommes", method = RequestMethod.GET)
    public String articlesHommes(Model model) {
        List<Article> List = articlesDao.ListHommes();
        model.addAttribute("article", List);
        return "article/categorie_hommes";
    }

    @RequestMapping(value = "article", method = RequestMethod.GET)
    public String articleForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/add";
    }

    @RequestMapping(value = "article", method = RequestMethod.POST)
    public String articleSubmit(@ModelAttribute Article article, Model model) {
        articlesDao.add(article);
        model.addAttribute("article", article);
        return "redirect:/articles";
    }

    @RequestMapping(value = "article/{id}", method = RequestMethod.GET)
    public String showArticle(@PathVariable int id, Model model) {
        Article article = articlesDao.show(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    @RequestMapping(value = "article/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("article", articlesDao.show(id));
        return "article/edit";
    }

    @RequestMapping(value = "article/edit/{id}", method = RequestMethod.POST)
    public String editArticle(@ModelAttribute Article article, @PathVariable int id) {
        articlesDao.edit(article, id);
        return "redirect:/articles";
    }

    /** This function makes it possible to remove an article from the database
     *
     * @param id
     * @return page html to redirect after deleted an article
     */
    @RequestMapping(value = "article/delete/{id}", method = RequestMethod.GET)
    public String articleDelete(@PathVariable int id) {
        int article = articlesDao.delete(id);
        return "redirect:/articles";
    }

    /**This fonction display 6 article on de first page
     *
     * @param model
     * @return page html firstPage.html
     */
    @RequestMapping(value = "firstPage", method = RequestMethod.GET)
    public String firstPage(Model model) {
        List<Article> List = articlesDao.firstPage();
        model.addAttribute("article", List);
        return "article/firstPage";
    }

    /**This fonction display 6 article on de second page
     *
     * @param model
     * @return page html secondPage.html
     */
    @RequestMapping(value = "/secondPage", method = RequestMethod.GET)
    public String secondPage(Model model) {
        List<Article> List = articlesDao.secondPage();
        model.addAttribute("article", List);
        return "article/secondPage";
    }

    /** This fonction display 6 article on de third page
     *
     * @param model
     * @return page html thirdPage.html
     */
    @RequestMapping(value = "/thirdPage", method = RequestMethod.GET)
    public String thirdPage(Model model) {
        List<Article> List = articlesDao.thirdPage();
        model.addAttribute("article", List);
        return "article/thirdPage";
    }

}
