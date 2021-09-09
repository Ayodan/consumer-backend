package com.olaolu.database.serviceAndDao;
import java.util.List;
import com.olaolu.database.model.Article;
public interface IArticleService {
    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    boolean addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
    List <Article> getArticlesList(int numbders,int offset);
}
