package com.example.ruedesplaisirs.services;
import com.example.ruedesplaisirs.models.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


//@Service
@Repository
public class ArticleDao {
    @Autowired //permet d'injecter la dépendance
    private JdbcTemplate jdbcTemplate;

    public List<Article> ListAll() {
        //List<Article> List = new ArrayList<>();

        String sql = "SELECT * FROM article;";
        List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));

        return list;
    }

    public int add(Article article){
        String sql = "INSERT INTO article (name, description, url_image, price, category_id) VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, article.getName(), article.getDescription(), article.getUrl_image(), article.getPrice(), article.getCategory_id());
    }

    public Article show(int id){
        String sql = "SELECT * FROM article WHERE id=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Article.class), id);
    }
}