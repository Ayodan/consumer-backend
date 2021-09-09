package com.olaolu.database.model;

import anotations.Author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private int articleId;

    @Author(message = "Provide author please")
    @NotEmpty(message = "Please provide a title")
    private String title;

    @NotEmpty(message = "Please provide a category")
    private String category;
    public int getArticleId() {
        return articleId;
    }
    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
