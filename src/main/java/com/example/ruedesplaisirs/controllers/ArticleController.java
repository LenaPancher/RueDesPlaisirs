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

    /**
     * This function makes it possible to display all the article from de database
     * @param model
     * @return index.html
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        List<Article> List = articlesDao.ListAll();
        model.addAttribute("article", List);
        return "article/index";
    }

    /**
     * This function displays the articles of the category 'Women'
     * @param model
     * @return categorie_femmes.html
     */
    @RequestMapping(value = "/categorie_femmes", method = RequestMethod.GET)
    public String articlesFemmes(Model model) {
        List<Article> List = articlesDao.ListFemmes();
        model.addAttribute("article", List);
        return "article/categorie_femmes";
    }

    /**
     * This function displays the articles of the category 'Men'
     * @param model
     * @return categorie_hommes.html
     */
    @RequestMapping(value = "/categorie_hommes", method = RequestMethod.GET)
    public String articlesHommes(Model model) {
        List<Article> List = articlesDao.ListHommes();
        model.addAttribute("article", List);
        return "article/categorie_hommes";
    }

    /**
     * This function retrieves the data from the article form when adding an article
     * @param model
     * @return add.html
     */
    @RequestMapping(value = "article", method = RequestMethod.GET)
    public String articleForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/add";
    }

    /**
     * This function checks if the data are well filled and then adds them in the data table
     * @param article
     * @param model
     * @return add.html
     */
    @RequestMapping(value = "article", method = RequestMethod.POST)
    public String articleSubmit(@ModelAttribute Article article, Model model) {
        if (article.getName() != null && article.getName().length() > 0
                && article.getDescription() != null && article.getDescription().length() > 0
                && article.getUrl_image() != null) {
            articlesDao.add(article);
            model.addAttribute("article", article);
            return "redirect:/articles";
        }
        model.addAttribute("errorMessage", "Nom, description et url d'image obligatoire");
        return "article/add";
    }

    /**
     * This function allows you to display the data of an article
     * @param id
     * @param model
     * @return show.html
     */
    @RequestMapping(value = "article/{id}", method = RequestMethod.GET)
    public String showArticle(@PathVariable int id, Model model) {
        Article article = articlesDao.show(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    /**
     * This function retrieves the data from the article form when an article is modified
     * @param id
     * @param model
     * @return edit.html
     */
    @RequestMapping(value = "article/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("article", articlesDao.show(id));
        return "article/edit";
    }

    /**
     * This function adds the changes to the database
     * @param article
     * @param id
     * @return index.html
     */
    @RequestMapping(value = "article/edit/{id}", method = RequestMethod.POST)
    public String editArticle(@ModelAttribute Article article, @PathVariable int id) {
        articlesDao.edit(article, id);
        return "redirect:/articles";
    }

    /**
     * This function makes it possible to remove an article from the database
     * @param id
     * @return page html to redirect after deleted an article
     */
    @RequestMapping(value = "article/delete/{id}", method = RequestMethod.GET)
    public String articleDelete(@PathVariable int id) {
        int article = articlesDao.delete(id);
        return "redirect:/articles";
    }

    /**
     * This fonction display 6 article on de first page
     * @param model
     * @return page html firstPage.html
     */
    @RequestMapping(value = "firstPage", method = RequestMethod.GET)
    public String firstPage(Model model) {
        List<Article> List = articlesDao.firstPage();
        model.addAttribute("article", List);
        return "article/firstPage";
    }

    /**
     * This fonction display 6 article on de second page
     * @param model
     * @return page html secondPage.html
     */
    @RequestMapping(value = "/secondPage", method = RequestMethod.GET)
    public String secondPage(Model model) {
        List<Article> List = articlesDao.secondPage();
        model.addAttribute("article", List);
        return "article/secondPage";
    }

    /**
     * This fonction display 6 article on de third page
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
