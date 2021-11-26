package com.example.ruedesplaisirs.services;
import com.example.ruedesplaisirs.models.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Service
@Repository
public class ArticlesDao {
    @Autowired //permet d'injecter la dépendance
    private JdbcTemplate jdbcTemplate;

    /**
     * This function retrieves all the elements of the 'article' table
     * @return List of articles
     */
    public List<Article> ListAll() {
        String sql = "SELECT * FROM article;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return List;
    }

    /**
     * This function retrieves the first 6 elements of the 'article' table from element 0
     * @return List of articles
     */
    public List<Article> firstPage() {
        String sql = "SELECT * FROM article LIMIT 0, 6;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return List;
    }

    /**
     * This function retrieves the 6 elements of the 'article' table from element 6
     * @return List of articles
     */
    public List<Article> secondPage() {
        String sql = "SELECT * FROM article LIMIT 6, 6;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return List;
    }

    /**
     * This function retrieves the 6 elements of the 'article' table from element 12
     * @return List of articles
     */
    public List<Article> thirdPage() {
        String sql = "SELECT * FROM article LIMIT 12, 6;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return List;
    }


    /**
     * This function allows you to add an item in the 'article' table
     * @param article
     */
    public void add(Article article){
        String sql = "INSERT INTO article (name, description, url_image, price, category_id) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, article.getName(), article.getDescription(), article.getUrl_image(), article.getPrice(), article.getCategory_id());
    }


    /**
     * This function allows to find an element according to its id of the 'article' table
     * @param id
     * @return request sql
     */
    public Article show(int id){
        String sql = "SELECT * FROM article WHERE id=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Article.class), id);
    }

    /**
     * This function allows you to modify an item in the 'article' table
     * @param article
     * @param id
     * @return request sql
     */
    public int edit(Article article, int id) {
        String sql = "UPDATE article SET name = ?, description = ?, url_image = ?, price = ?, category_id = ? WHERE id=?;";
        return jdbcTemplate.update(sql, article.getName(), article.getDescription(), article.getUrl_image(), article.getPrice(), article.getCategory_id(), id);
    }

    /**
     * This function allows you to delete an element in the 'article' table
     * @param id
     * @return request sql
     */
    public int delete(int id) {
        String sql = "DELETE FROM article WHERE id=?;";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * This function retrieves a list of items where the category is 'Femmes'.
     * @return list of articles
     */
    public List<Article> ListFemmes() {
        String sql = "SELECT * FROM `article` WHERE category_id = 1;";
        List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return list;
    }

    /**
     * This function retrieves a list of items where the category is 'Hommes'.
     * @return list of articles
     */
    public List<Article> ListHommes() {
        String sql = "SELECT * FROM `article` WHERE category_id = 2;";
        List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return list;
    }

    /**
     * This function retrieves a list of items where the category is 'Femmes et Hommes'.
     * @return list of articles
     */
    public List<Article> ListHommesEtFemmes() {
        String sql = "SELECT * FROM `article` WHERE category_id = 3;";
        List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return list;
    }
}