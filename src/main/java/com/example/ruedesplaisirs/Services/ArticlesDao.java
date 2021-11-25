package com.example.ruedesplaisirs.services;
import com.example.ruedesplaisirs.models.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//@Service
@Repository
public class ArticlesDao {
    @Autowired //permet d'injecter la dépendance
    private JdbcTemplate jdbcTemplate;

    public List<Article> ListAll() {
        String sql = "SELECT * FROM article;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));

        return List;
    }
    public List<Article> firstPage() {
        String sql = "SELECT * FROM article LIMIT 0, 6;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));

        return List;
    }

    public List<Article> secondPage() {
        //String sql = "SELECT * FROM article;";
        String sql = "SELECT * FROM article LIMIT 6, 6;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));

        return List;
    }

    public List<Article> thirdPage() {
        //String sql = "SELECT * FROM article;";
        String sql = "SELECT * FROM article LIMIT 12, 6;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));

        return List;
    }

    public int add(Article article){
        String sql = "INSERT INTO article (name, description, url_image, price, category_id) VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, article.getName(), article.getDescription(), article.getUrl_image(), article.getPrice(), article.getCategory_id());
    }

    public Article show(int id){
        String sql = "SELECT * FROM article WHERE id=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Article.class), id);
    }

    public int edit(Article article, int id) {
        String sql = "UPDATE article SET name = ?, description = ?, url_image = ?, price = ?, category_id = ? WHERE id=?;";
        return jdbcTemplate.update(sql, article.getName(), article.getDescription(), article.getUrl_image(), article.getPrice(), article.getCategory_id(), id);
    }

    public int delete(int id) {
        String sql = "DELETE FROM article WHERE id=?;";
        return jdbcTemplate.update(sql, id);
    }

    public List<Article> ListFemmes() {
        String sql = "SELECT * FROM `article` WHERE category_id = 1;";
        List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return list;
    }

    public List<Article> ListHommes() {
        String sql = "SELECT * FROM `article` WHERE category_id = 2;";
        List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return list;
    }


}