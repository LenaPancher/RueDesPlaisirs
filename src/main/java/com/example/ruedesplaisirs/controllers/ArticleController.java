package com.example.ruedesplaisirs.controllers;

import com.example.ruedesplaisirs.models.Article;

import com.example.ruedesplaisirs.models.Categorie;
import com.example.ruedesplaisirs.services.ArticlesDao;
import com.example.ruedesplaisirs.services.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticlesDao articlesDao;

    @Autowired
    private CategoryDao categoryDao;

    /**
     * This function makes it possible to display all the article from de database
     *
     * @param model
     * @return index.html
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam("category_id") Optional<Integer> category_id, @RequestParam("tri") Optional<String> tri, @RequestParam("page") Optional<Integer> page) {
        List<Article> articles = articlesDao.getArticles(category_id, tri);
        List<Categorie> categorieList = categoryDao.getCategories();
        if (page.isPresent()) {
            articles = articlesDao.Pagination(page);
        }
        model.addAttribute("articles", articles);
        model.addAttribute("category", categorieList);
        return "article/index";
    }

    /**
     * This function retrieves the data from the article form when adding an article
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param id
     * @return page html to redirect after deleted an article
     */
    @RequestMapping(value = "article/delete/{id}", method = RequestMethod.GET)
    public String articleDelete(@PathVariable int id) {
        int article = articlesDao.delete(id);
        return "redirect:/articles";
    }

}
