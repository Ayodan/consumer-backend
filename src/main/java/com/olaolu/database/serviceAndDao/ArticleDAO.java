package com.olaolu.database.serviceAndDao;

import com.olaolu.database.exceptions.CustomGlobalExceptionHandler;
import com.olaolu.database.model.Article;
import com.olaolu.database.model.ArticleRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CacheConfig(cacheNames = {"articles"})
@Transactional
@Repository
public class ArticleDAO implements IArticleDAO {
    private static final Logger log= LoggerFactory.getLogger(ArticleDAO.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    private SimpleJdbcCall simpleJdbcCallRefCursor;


    @PostConstruct
    public void intitializeSimpleJdbc(){
        log.info("Simple jdbc call intialised");

    }

    @Override
    public Article getArticleById(int articleId) throws DataAccessException {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        simpleJdbcCallRefCursor=new SimpleJdbcCall(jdbcTemplate).withProcedureName("getArticleById").returningResultSet("single",rowMapper);
        //jdbcTemplate.execute(createProcedure);
        SqlParameterSource paramaters = new MapSqlParameterSource()
                .addValue("id", articleId);
        Map out = simpleJdbcCallRefCursor.execute(paramaters);
        log.info("result",out);
        ArrayList arrayList= (ArrayList) out.get("single");
        if (arrayList.size()<1){
            throw new CustomGlobalExceptionHandler.EmptyListObject("Object Id not found");
        }else {
            return (Article) arrayList.get(0);
        }

    }


    @Override
    @Cacheable
    public List<Article> getAllArticles() {
        simulateSlowService();
        String sql = "SELECT articleId, title, category FROM articles";
        //RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        RowMapper<Article> rowMapper = new ArticleRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }
    @Override
    public void addArticle(Article article) {
        String sql = " SET IDENTITY_INSERT articles on ;INSERT INTO articles (articleId, title, category) values (?, ?, ?); SET IDENTITY_INSERT articles OFF";
        jdbcTemplate.update(sql, article.getArticleId(), article.getTitle(), article.getCategory());
        sql = "SELECT articleId FROM articles WHERE title = ? and category=?";
        int articleId = jdbcTemplate.queryForObject(sql, Integer.class, article.getTitle(), article.getCategory());
        article.setArticleId(articleId);
    }
    @CachePut
    @Override
    public void updateArticle(Article article) throws DataAccessException {
        String sql = "UPDATE articles SET title=?, category=? WHERE articleId=?";
        jdbcTemplate.update(sql, article.getTitle(), article.getCategory(), article.getArticleId());
    }
    @Override
    public void deleteArticle(int articleId) throws DataAccessException{
        String sql = "DELETE FROM articles WHERE articleId=?";
        jdbcTemplate.update(sql, articleId);
    }

    @Override
    public List<Article> getArticlesList(int numbders, int offset) {
        RowMapper<Article> rowMapper = new ArticleRowMapper();
        simpleJdbcCallRefCursor=new SimpleJdbcCall(jdbcTemplate).withProcedureName("getRangeOfResult").returningResultSet("multiple",rowMapper);
        SqlParameterSource paramaters = new MapSqlParameterSource()
                .addValue("number", numbders).addValue("offset",offset);
        Map out = simpleJdbcCallRefCursor.execute(paramaters);
        return (List<Article>) out.get("multiple");
    }

    @Override
    public boolean articleExists(String title, String category) {
        String sql = "SELECT count(*) FROM articles WHERE title = ? and category=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, title, category);

        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}