package com.example.ruedesplaisirs.services;

import com.example.ruedesplaisirs.controllers.ArticleController;
import com.example.ruedesplaisirs.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int add(Article article){
        String sql = "INSERT INTO article (name, description, url_image, price, category_id) VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, article.getName(), article.getDescription(), article.getUrl_image(), article.getPrice(), article.getCategory_id());
    }

}
