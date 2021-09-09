package com.olaolu.database.model;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ArticleRowMapper implements RowMapper<Article> {
//        @Override
//        public Article mapRow(ResultSet row, int rowNum)  {
//            if (row==null){
//                Article article = new Article();
//                article.setArticleId(0);
//                article.setTitle("none");
//                article.setCategory("no cat");
//                return article;
//            }else {
//                try {
//                    Article article = new Article();
//                    article.setArticleId(row.getInt("articleId"));
//                    article.setTitle(row.getString("title"));
//                    article.setCategory(row.getString("category"));
//                    return article;
//
//                }catch (Exception e){
//                    Article article = new Article();
//                    article.setArticleId(0);
//                    article.setTitle(e.getMessage());
//                    article.setCategory(e.getMessage());
//                    return article;
//                }
//
//            }
//
//
//        }


    @Override
    public Article mapRow(ResultSet row, int rowNum)  {
        Article article = new Article();

        if (row==null){
            article.setArticleId(0);
            article.setTitle("none");
            article.setCategory("no cat");
            return article;
        }

            try {
                article.setArticleId(row.getInt("articleId"));
                article.setTitle(row.getString("title"));
                article.setCategory(row.getString("category"));
                return article;

            }catch (Exception e){
                article.setArticleId(0);
                article.setTitle(e.getMessage());
                article.setCategory(e.getMessage());
                return article;
            }




    }

}
