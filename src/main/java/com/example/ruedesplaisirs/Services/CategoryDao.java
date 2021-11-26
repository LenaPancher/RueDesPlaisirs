package com.example.ruedesplaisirs.services;

import com.example.ruedesplaisirs.models.Article;
import com.example.ruedesplaisirs.models.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Service
@Repository
public class CategoryDao {
    @Autowired //permet d'injecter la dépendance
    private JdbcTemplate jdbcTemplate;


    /**
     * his function retrieves all the elements of the 'categorie' table
     * @return list of categories
     */
    public List<Categorie> getCategories() {
        String sql = "SELECT * FROM `categorie`;";
        List<Categorie> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Categorie.class));
        return list;
    }

}