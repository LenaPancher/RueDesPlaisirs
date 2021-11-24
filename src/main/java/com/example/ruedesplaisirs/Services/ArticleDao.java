package com.example.ruedesplaisirs.Services;
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
}