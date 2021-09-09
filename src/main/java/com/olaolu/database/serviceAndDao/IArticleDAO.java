package com.olaolu.database.serviceAndDao;
import com.olaolu.database.model.Article;
import java.util.List;
public interface IArticleDAO {
    List<Article> getAllArticles();

    Article getArticleById(int articleId);

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);
    List <Article> getArticlesList(int numbders,int offset);

    boolean articleExists(String title, String category);
}