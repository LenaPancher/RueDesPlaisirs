package com.example.ruedesplaisirs.models;

public class Article {
    private long id;
    private String name;
    private String description;
    private String url_image;
    private int price;
    private int category_id;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl_image() {
        return url_image;
    }

    public int getPrice() {
        return price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
