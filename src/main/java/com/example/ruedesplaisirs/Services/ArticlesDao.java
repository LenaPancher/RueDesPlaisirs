package com.example.ruedesplaisirs.services;

import com.example.ruedesplaisirs.models.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//@Service
@Repository
public class ArticlesDao {
    @Autowired //permet d'injecter la dépendance
    private JdbcTemplate jdbcTemplate;

    /**
     * This function retrieves all the elements of the 'article' table
     *
     * @return List of articles
     */
    public List<Article> ListAll() {
        String sql = "SELECT * FROM article;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
        return List;
    }

    /**
     * This function retrieves the first 6 elements of the 'article' table from element 0
     *
     * @return List of articles
     */
    public List<Article> Pagination(Optional<Integer> index) {
        String sql = "SELECT * FROM article LIMIT ?, 6;";
        List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class), index.get());
        return List;
    }

    /**
     * This function allows you to add an item in the 'article' table
     *
     * @param article
     */
    public void add(Article article) {
        String sql = "INSERT INTO article (name, description, url_image, price, category_id) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, article.getName(), article.getDescription(), article.getUrl_image(), article.getPrice(), article.getCategory_id());
    }


    /**
     * This function allows to find an element according to its id of the 'article' table
     *
     * @param id
     * @return request sql
     */
    public Article show(int id) {
        String sql = "SELECT * FROM article WHERE id=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Article.class), id);
    }

    /**
     * This function allows you to modify an item in the 'article' table
     *
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
     *
     * @param id
     * @return request sql
     */
    public int delete(int id) {
        String sql = "DELETE FROM article WHERE id=?;";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * This function retrieves elements from the 'article' table according to the given parameters
     * @param id_category
     * @param tri
     * @return list
     */
    public List<Article> getArticles(Optional<Integer> id_category, Optional<String> tri) {
        if (id_category.isPresent() && tri.isPresent()) {
            String sql;
            if (tri.get().equals("ASC")) {
                sql = "SELECT * FROM `article` WHERE category_id = ? ORDER BY price ASC;";
            } else {
                sql = "SELECT * FROM `article` WHERE category_id = ? ORDER BY price DESC;";

            }
            List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class), id_category.get());
            return list;
        }
        if (id_category.isPresent()) {
            String sql = "SELECT * FROM `article` WHERE category_id = ?;";
            List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class), id_category.get());
            return list;
        }
        if (tri.isPresent()) {
            String sql;
            if (tri.get().equals("ASC")) {
                sql = "SELECT * FROM `article` ORDER BY price ASC;";
            } else {
                sql = "SELECT * FROM `article` ORDER BY price DESC;";
            }
            List<Article> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
            System.out.println(sql);
            return list;
        } else {
            String sql = "SELECT * FROM article;";
            List<Article> List = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Article.class));
            return List;
        }
    }
}