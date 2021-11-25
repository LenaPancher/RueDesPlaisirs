package com.example.ruedesplaisirs;

import com.example.ruedesplaisirs.models.Article;
import com.example.ruedesplaisirs.models.Categorie;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class RueDesPlaisirsApplicationTests {

    Article article;
    Categorie categorie;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Avant le démarrage");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("Après tous les tests");
    }

    @BeforeEach
    public void setUp() throws Exception {
        categorie = new Categorie();
        categorie.setId(1);
        categorie.setName("Femme");

        article = new Article();
        article.setId(1);
        article.setName("Chapelet");
        article.setDescription("Perles anales");
        article.setUrl_image("https://www.espacelibido.com/media/catalog/product/cache/1/image/800x1080/5e06319eda06f020e43594a9c230972d/2/0/20110110-02_1_1/Chapelet-Anal-Translucide-X-10-31.jpg");
        article.setPrice(8);
        article.setCategory_id(1);

        System.out.println("Avant un test");
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("Après un test");
    }

    @Test
    public void testArticleProperties() throws Exception {
        assertThat(article, hasProperty("id"));
        assertThat(article, hasProperty("id", is(1L)));

        assertThat(article, hasProperty("name"));
        assertThat(article, hasProperty("name", is("Chapelet")));

        assertThat(article, hasProperty("description"));
        assertThat(article, hasProperty("description", is("Perles anales")));

        assertThat(article, hasProperty("url_image"));
        assertThat(article, hasProperty("url_image", is("https://www.espacelibido.com/media/catalog/product/cache/1/image/800x1080/5e06319eda06f020e43594a9c230972d/2/0/20110110-02_1_1/Chapelet-Anal-Translucide-X-10-31.jpg")));

        assertThat(article, hasProperty("price"));
        assertThat(article, hasProperty("price", is(8)));

        assertThat(article, hasProperty("category_id"));
        assertThat(article, hasProperty("category_id", is(1)));
    }

    @Test
    public void testCategorieProperties() throws Exception {
        assertThat(categorie, hasProperty("id"));
        assertThat(categorie, hasProperty("id", is(1L)));

        assertThat(categorie, hasProperty("name"));
        assertThat(categorie, hasProperty("name", is("Femme")));
    }



}
