package com.olaolu.database.controllers;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.olaolu.database.exceptions.CustomGlobalExceptionHandler;
import com.olaolu.database.serviceAndDao.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.olaolu.database.model.Article;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("user")
@Validated
public class ArticleController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    Date date;

    @Autowired
    private IArticleService articleService;
    @RequestMapping(value = "articles/{numbers}/{offset}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Article>> getAllArticlesInRange(@PathVariable("numbers") int numbers,@PathVariable( "offset")int offset) {
        List<Article> list = articleService.getArticlesList(numbers,offset);
        LocalDateTime localDateTime;
        //localDateTime.plusD

        return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
    }
    @GetMapping("article/{id}")
    public ResponseEntity<Article> getArticleById( @PathVariable("id") @Min(1) Integer id) {
        Article article = articleService.getArticleById(id);
            return new ResponseEntity<Article>(article, HttpStatus.OK);
    }
    @GetMapping("articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = articleService.getAllArticles();
        return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
    }
    @PostMapping("article")
    public ResponseEntity<Void> addArticle(@Valid @RequestBody Article article, UriComponentsBuilder builder) {
        boolean flag = articleService.addArticle(article);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @PutMapping("article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }
    @DeleteMapping("article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
